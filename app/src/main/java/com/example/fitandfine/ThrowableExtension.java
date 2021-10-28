package com.example.fitandfine;


import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public final class ThrowableExtension {

    static final AbstractDesugaringStrategy STRATEGY;

    public static final String SYSTEM_PROPERTY_TWR_DISABLE_MIMIC =
            "com.google.devtools.build.android.desugar.runtime.twr_disable_mimic";

    static {
        AbstractDesugaringStrategy strategy;
        try {
            Integer apiLevel = readApiLevelFromBuildVersion();
            if (apiLevel != null && apiLevel.intValue() >= 19) {
                strategy = new ReuseDesugaringStrategy();
            } else if (useMimicStrategy()) {
                strategy = new MimicDesugaringStrategy();
            } else {
                strategy = new NullDesugaringStrategy();
            }
        } catch (Throwable e) {
            System.err.println(
                    "An error has occured when initializing the try-with-resources desuguring strategy. "
                            + "The default strategy "
                            + NullDesugaringStrategy.class.getName()
                            + "will be used. The error is: ");
            e.printStackTrace(System.err);
            strategy = new NullDesugaringStrategy();
        }
        STRATEGY = strategy;
    }

    public static  AbstractDesugaringStrategy getStrategy() {
        return STRATEGY;
    }

    public static      void addSuppressed(Throwable receiver, Throwable suppressed) {
        STRATEGY.addSuppressed(receiver, suppressed);
    }

    public static      Throwable[] getSuppressed(Throwable receiver) {
        return STRATEGY.getSuppressed(receiver);
    }

    public static      void printStackTrace(Throwable receiver) {
        STRATEGY.printStackTrace(receiver);
    }

    public static      void printStackTrace(Throwable receiver, PrintWriter writer) {
        STRATEGY.printStackTrace(receiver, writer);
    }

    public static      void printStackTrace(Throwable receiver, PrintStream stream) {
        STRATEGY.printStackTrace(receiver, stream);
    }

    private static  boolean useMimicStrategy() {
        return !Boolean.getBoolean(SYSTEM_PROPERTY_TWR_DISABLE_MIMIC);
    }

    private static final String ANDROID_OS_BUILD_VERSION = "android.os.Build$VERSION";


    private static  Integer readApiLevelFromBuildVersion() {
        try {
            Class<?> buildVersionClass = Class.forName(ANDROID_OS_BUILD_VERSION);
            Field field = buildVersionClass.getField("SDK_INT");
            return (Integer) field.get(null);
        } catch (Exception e) {
            System.err.println(
                    "Failed to retrieve value from "
                            + ANDROID_OS_BUILD_VERSION
                            + ".SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }

    abstract static class AbstractDesugaringStrategy {

        protected static final Throwable[] EMPTY_THROWABLE_ARRAY = new Throwable[0];

        public abstract void addSuppressed(Throwable receiver, Throwable suppressed);

        public abstract Throwable[] getSuppressed(Throwable receiver);

        public abstract void printStackTrace(Throwable receiver);

        public abstract void printStackTrace(Throwable receiver, PrintStream stream);

        public abstract void printStackTrace(Throwable receiver, PrintWriter writer);
    }

    /** This strategy just delegates all the method calls to java.lang.Throwable. */
    static final class ReuseDesugaringStrategy extends AbstractDesugaringStrategy {

        @Override
        public void addSuppressed(Throwable receiver, Throwable suppressed) {
            receiver.addSuppressed(suppressed);
        }

        @Override
        public Throwable[] getSuppressed(Throwable receiver) {
            return receiver.getSuppressed();
        }

        @Override
        public void printStackTrace(Throwable receiver) {
            receiver.printStackTrace();
        }

        @Override
        public void printStackTrace(Throwable receiver, PrintStream stream) {
            receiver.printStackTrace(stream);
        }

        @Override
        public void printStackTrace(Throwable receiver, PrintWriter writer) {
            receiver.printStackTrace(writer);
        }
    }

    static final class MimicDesugaringStrategy extends AbstractDesugaringStrategy {

        static final String SUPPRESSED_PREFIX = "Suppressed: ";
        private final ConcurrentWeakIdentityHashMap map = new ConcurrentWeakIdentityHashMap();

        @Override
        public void addSuppressed(Throwable receiver, Throwable suppressed) {
            if (suppressed == receiver) {
                throw new IllegalArgumentException("Self suppression is not allowed.", suppressed);
            }
            if (suppressed == null) {
                throw new NullPointerException("The suppressed exception cannot be null.");
            }
            map.get(receiver, /*createOnAbsence=*/true).add(suppressed);
        }

        @Override
        public Throwable[] getSuppressed(Throwable receiver) {
            List<Throwable> list = map.get(receiver, /*createOnAbsence=*/false);
            if (list == null || list.isEmpty()) {
                return EMPTY_THROWABLE_ARRAY;
            }
            return list.toArray(EMPTY_THROWABLE_ARRAY);
        }


        @Override
        public void printStackTrace(Throwable receiver) {
            receiver.printStackTrace();
            List<Throwable> suppressedList = map.get(receiver, /*createOnAbsence=*/false);
            if (suppressedList == null) {
                return;
            }
            synchronized (suppressedList) {
                for (Throwable suppressed : suppressedList) {
                    System.err.print(SUPPRESSED_PREFIX);
                    suppressed.printStackTrace();
                }
            }
        }

        @Override
        public void printStackTrace(Throwable receiver, PrintStream stream) {
            receiver.printStackTrace(stream);
            List<Throwable> suppressedList = map.get(receiver, false);
            if (suppressedList == null) {
                return;
            }
            synchronized (suppressedList) {
                for (Throwable suppressed : suppressedList) {
                    stream.print(SUPPRESSED_PREFIX);
                    suppressed.printStackTrace(stream);
                }
            }
        }

        @Override
        public void printStackTrace(Throwable receiver, PrintWriter writer) {
            receiver.printStackTrace(writer);
            List<Throwable> suppressedList = map.get(receiver, false);
            if (suppressedList == null) {
                return;
            }
            synchronized (suppressedList) {
                for (Throwable suppressed : suppressedList) {
                    writer.print(SUPPRESSED_PREFIX);
                    suppressed.printStackTrace(writer);
                }
            }
        }

    }



    static final class ConcurrentWeakIdentityHashMap {

        private final ConcurrentHashMap<WeakKey, List<Throwable>> map =
                new ConcurrentHashMap<>(16, 0.75f, 10);
        private final ReferenceQueue<Throwable> referenceQueue = new ReferenceQueue<>();
        public  List<Throwable> get(Throwable throwable, boolean createOnAbsence) {
            deleteEmptyKeys();
            WeakKey keyForQuery = new WeakKey(throwable, null);
            List<Throwable> list = map.get(keyForQuery);
            if (!createOnAbsence) {
                return list;
            }
            if (list != null) {
                return list;
            }
            List<Throwable> newValue = new Vector<>(2);
            list = map.putIfAbsent(new WeakKey(throwable, referenceQueue), newValue);
            return list == null ? newValue : list;
        }


        int size() {
            return map.size();
        }

        void deleteEmptyKeys() {

            for (Reference<?> key = referenceQueue.poll(); key != null; key = referenceQueue.poll()) {
                map.remove(key);
            }
        }

        private static final class WeakKey extends WeakReference<Throwable> {

            private final int hash;

            public WeakKey(Throwable referent, ReferenceQueue<Throwable> q) {
                super(referent, q);
                if (referent == null) {
                    throw new NullPointerException("The referent cannot be null");
                }
                hash = System.identityHashCode(referent);
            }

            @Override
            public  int hashCode() {
                return hash;
            }

            @Override
            public  boolean equals(Object obj) {
                if (obj == null || obj.getClass() != getClass()) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                WeakKey other = (WeakKey) obj;
                return this.hash == other.hash && this.get() == other.get();
            }
        }
    }

    static final class NullDesugaringStrategy extends AbstractDesugaringStrategy {

        @Override
        public void addSuppressed(Throwable receiver, Throwable suppressed) {

        }

        @Override
        public Throwable[] getSuppressed(Throwable receiver) {
            return EMPTY_THROWABLE_ARRAY;
        }

        @Override
        public void printStackTrace(Throwable receiver) {
            receiver.printStackTrace();
        }

        @Override
        public void printStackTrace(Throwable receiver, PrintStream stream) {
            receiver.printStackTrace(stream);
        }

        @Override
        public void printStackTrace(Throwable receiver, PrintWriter writer) {
            receiver.printStackTrace(writer);
        }
    }
}




