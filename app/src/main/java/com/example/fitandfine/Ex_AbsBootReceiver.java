package com.example.fitandfine;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import androidx.core.app.NotificationCompat;
import android.util.Log;
//import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

import com.example.fitandfine.Ex_alarm.Ex_AlarmHelper;
import com.example.fitandfine.Ex_alarm.Ex_NotificationPublisher;
import com.example.fitandfine.Ex_alarm.Ex_Reminder_custom;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Ex_AbsBootReceiver extends BroadcastReceiver {
    private static final int REQUEST_CODE = 1234;
    private static final String TAG = "Ex_AbsBootReceiver";
    private Ex_AlarmHelper exAlarmHelper;
    private Context context;
    private SharedPreferences sharedPreferences;
    private SimpleDateFormat startTimeFormat;

    /* renamed from: com.outthinking.abs.Ex_AbsBootReceiver$1 */
    class C12561 extends TypeToken<List<Ex_Reminder_custom>> {
        C12561() {
        }
    }

    private PendingIntent getPendingIntent() {
        Intent intent = new Intent("com.outthinking.weightloss.alarmmanagerdemo.NOTIFY_ACTION");
        intent.setClass(this.context, Ex_NotificationPublisher.class);
      //  intent.setFlags(ErrorDialogData.BINDER_CRASH);
        return PendingIntent.getBroadcast(this.context, REQUEST_CODE, intent, 134217728);
    }

    private void setBootReceiverEnabled(int i) {
        Log.d(TAG, "setBootReceiverEnabled: ");
        this.context.getPackageManager().setComponentEnabledSetting(new ComponentName(this.context, Ex_AbsBootReceiver.class), i, 1);
    }
//
    public SimpleDateFormat getHourFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh");
        this.startTimeFormat = simpleDateFormat;
        return simpleDateFormat;
    }

    public SimpleDateFormat getMinuteFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm");
        this.startTimeFormat = simpleDateFormat;
        return simpleDateFormat;
    }

    public void onReceive(Context context, Intent intent) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onReceive - Intent Action: ");
        stringBuilder.append(intent.getAction());
        Log.d(str, stringBuilder.toString());
        try {
            this.context = context;
            setAlarm(context);
        } catch (Throwable e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    void schedulePendingIntent(int i, int i2, int i3) {
        Log.d(TAG, "schedulePendingIntent: ");
        Calendar instance = Calendar.getInstance();
        instance.set(11, i);
        instance.set(12, i2);
        instance.set(13, 0);
        instance.set(14, 0);
        instance.set(9, i3);
        scheduling_PendingIntent(instance.getTimeInMillis(), getPendingIntent());
    }

    void scheduling_PendingIntent(long j, PendingIntent pendingIntent) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("scheduling_PendingIntent: ");
        stringBuilder.append(j);
        stringBuilder.append("/");
        stringBuilder.append(pendingIntent);
        Log.d(str, stringBuilder.toString());
        AlarmManager alarmManager = (AlarmManager) this.context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (VERSION.SDK_INT >= 23) {
            Log.d(TAG, "setExactAndAllowWhileIdle");
            alarmManager.setExactAndAllowWhileIdle(0, j, pendingIntent);
        } else if (VERSION.SDK_INT >= 19) {
            Log.d(TAG, "setExact");
            alarmManager.setExact(0, j, pendingIntent);
        } else {
            Log.d(TAG, "set");
            alarmManager.set(0, j, pendingIntent);
        }
        setBootReceiverEnabled(1);
    }

    public void setAlarm(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        List list = (List) new Gson().fromJson(this.sharedPreferences.getString("Reminder_customObjectList", null), new C12561().getType());
        if (list != null) {
            this.exAlarmHelper = new Ex_AlarmHelper(context);
            Calendar instance = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
            for (int i = 0; i < list.size(); i++) {
                try {
                    instance.setTime(simpleDateFormat.parse(((Ex_Reminder_custom) list.get(i)).gettime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("setAlarm: hr===");
                stringBuilder.append(instance.get(10));
                stringBuilder.append("min==");
                stringBuilder.append(instance.get(12));
                Log.d(str, stringBuilder.toString());
                setTimeHrAndMin(this.exAlarmHelper, instance);
            }
        }
    }

    void setTimeHrAndMin(Ex_AlarmHelper r4, Calendar r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:6:0x00b5 in {2, 4, 5} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/775386112.run(Unknown Source)
*/
        /*
        r3 = this;
        r4 = TAG;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "hr";
        r0.append(r1);
        r1 = r3.getHourFormat();
        r2 = r5.getTime();
        r1 = r1.format(r2);
        r1 = java.lang.Integer.parseInt(r1);
        r0.append(r1);
        r0 = r0.toString();
        android.util.Log.d(r4, r0);
        r4 = TAG;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "min";
        r0.append(r1);
        r1 = r3.getMinuteFormat();
        r2 = r5.getTime();
        r1 = r1.format(r2);
        r1 = java.lang.Integer.parseInt(r1);
        r0.append(r1);
        r0 = r0.toString();
        android.util.Log.d(r4, r0);
        r4 = r3.start_TimeFormat();
        r0 = r5.getTime();
        r4 = r4.format(r0);
        r0 = "AM";
        r4 = r4.endsWith(r0);
        if (r4 == 0) goto L_0x008c;
    L_0x0060:
        r4 = TAG;
        r0 = "am";
        android.util.Log.d(r4, r0);
        r4 = r3.getHourFormat();
        r0 = r5.getTime();
        r4 = r4.format(r0);
        r4 = java.lang.Integer.parseInt(r4);
        r0 = r3.getMinuteFormat();
        r5 = r5.getTime();
        r5 = r0.format(r5);
        r5 = java.lang.Integer.parseInt(r5);
        r0 = 0;
    L_0x0088:
        r3.schedulePendingIntent(r4, r5, r0);
        return;
    L_0x008c:
        r4 = TAG;
        r0 = "pm";
        android.util.Log.d(r4, r0);
        r4 = r3.getHourFormat();
        r0 = r5.getTime();
        r4 = r4.format(r0);
        r4 = java.lang.Integer.parseInt(r4);
        r0 = r3.getMinuteFormat();
        r5 = r5.getTime();
        r5 = r0.format(r5);
        r5 = java.lang.Integer.parseInt(r5);
        r0 = 1;
        goto L_0x0088;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.outthinking.abs.Ex_AbsBootReceiver.setTimeHrAndMin(com.outthinking.abs.alarm.alarmmanagerdemo.Ex_AlarmHelper, java.util.Calendar):void");
    }

    public SimpleDateFormat start_TimeFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        this.startTimeFormat = simpleDateFormat;
        return simpleDateFormat;
    }
}
