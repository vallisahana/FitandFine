package com.example.fitandfine.Ex_alarm;

import android.app.Notification.Builder;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;
import android.util.Log;
//import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.example.fitandfine.Ex_activities.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.fitandfine.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Ex_NotificationPublisher extends BroadcastReceiver {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String PREFERENCE_LAST_NOTIF_ID = "PREFERENCE_LAST_NOTIF_ID";
    private String TAG = "Ex_NotificationPublisher";
    private Ex_AlarmHelper exAlarmHelper;
    private Context context;
    private SharedPreferences sharedPreferences;
    private SimpleDateFormat startTimeFormat;

    /* renamed from: com.outthinking.abs.alarm.alarmmanagerdemo.Ex_NotificationPublisher$1 */
    class C12671 extends TypeToken<List<Ex_Reminder_custom>> {
        C12671() {
        }
    }

    private int getNextNotifId() {
        int i = this.sharedPreferences.getInt(PREFERENCE_LAST_NOTIF_ID, 0) + 1;
        if (i == Integer.MAX_VALUE) {
            i = 0;
        }
        this.sharedPreferences.edit().putInt(PREFERENCE_LAST_NOTIF_ID, i).apply();
        return i;
    }

    private void startNotification(Context context) {
        if (VERSION.SDK_INT < 26) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            Intent intent = new Intent(context, MainActivity.class);
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
           // intent.addFlags(ErrorDialogData.BINDER_CRASH);
            notificationManager.notify(getNextNotifId(), new Builder(context).setContentIntent(PendingIntent.getActivity(context, getNextNotifId(), intent, 0)).setSmallIcon(R.drawable.neo_abs_workout_noti_icon).setAutoCancel(true).setContentTitle("Hey! it's ABS Workout time").setContentText("Let's build ABS today.").setDefaults(1).build());
            return;
        }
        Intent intent2 = new Intent(context, MainActivity.class);
        intent2.setAction("android.intent.action.MAIN");
        intent2.addCategory("android.intent.category.LAUNCHER");
      //  intent2.addFlags(ErrorDialogData.BINDER_CRASH);
        NotificationManager notificationManager2 = (NotificationManager) context.getSystemService("notification");
        String str = "my_channel_id_01";
        NotificationChannel notificationChannel = new NotificationChannel(str, "My Notifications", 4);
        notificationChannel.setDescription("Channel description");
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(SupportMenu.CATEGORY_MASK);
        notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
        notificationChannel.enableVibration(true);
        notificationManager2.createNotificationChannel(notificationChannel);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, str);
        builder.setAutoCancel(true).setContentIntent(PendingIntent.getActivity(context, getNextNotifId(), intent2, 0)).setDefaults(-1).setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.neo_abs_workout_noti_icon).setContentTitle("Hey! it's ABS Workout time").setContentText("Let's build ABS today.").setDefaults(1);
        notificationManager2.notify(getNextNotifId(), builder.build());
    }

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
        String str = this.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onReceive ==========");
        stringBuilder.append(intent.getAction());
        Log.d(str, stringBuilder.toString());
        this.context = context;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        List list = (List) new Gson().fromJson(this.sharedPreferences.getString("Reminder_customObjectList", null), new C12671().getType());
        Calendar instance = Calendar.getInstance();
        instance.get(11);
        instance.get(12);
        int i = instance.get(7);
        int i2 = 0;
        while (i2 < list.size()) {
            if (((Ex_Reminder_custom) list.get(i2)).gettime().equals(startTimeFormat().format(instance.getTime())) && ((Ex_Reminder_custom) list.get(i2)).getOnoff()) {
                if (!(((Ex_Reminder_custom) list.get(i2)).getSun() && i == 1)) {
                    if (!((Ex_Reminder_custom) list.get(i2)).getMon() || i != 2) {
                        if (!((Ex_Reminder_custom) list.get(i2)).getTue() || i != 3) {
                            if (!((Ex_Reminder_custom) list.get(i2)).getWen() || i != 4) {
                                if (!((Ex_Reminder_custom) list.get(i2)).getThr() || i != 5) {
                                    if (!((Ex_Reminder_custom) list.get(i2)).getFri() || i != 6) {
                                        if (((Ex_Reminder_custom) list.get(i2)).getSat() && i == 7) {
                                        }
                                        this.exAlarmHelper = new Ex_AlarmHelper(context);
                                        setTimeHrAndMin(this.exAlarmHelper, instance);
                                    }
                                }
                            }
                        }
                    }
                }
                startNotification(context);
                this.exAlarmHelper = new Ex_AlarmHelper(context);
                setTimeHrAndMin(this.exAlarmHelper, instance);
            }
            i2++;
        }
    }

    void setTimeHrAndMin(Ex_AlarmHelper r8, Calendar r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:6:0x005f in {2, 4, 5} preds:[]
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
        r7 = this;
        r0 = r7.startTimeFormat();
        r1 = r9.getTime();
        r0 = r0.format(r1);
        r1 = "AM";
        r0 = r0.endsWith(r1);
        if (r0 == 0) goto L_0x003d;
    L_0x0014:
        r0 = r7.getHourFormat();
        r1 = r9.getTime();
        r0 = r0.format(r1);
        r2 = java.lang.Integer.parseInt(r0);
        r0 = r7.getMinuteFormat();
        r9 = r9.getTime();
        r9 = r0.format(r9);
        r3 = java.lang.Integer.parseInt(r9);
        r4 = 0;
    L_0x0035:
        r5 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
        r1 = r8;
        r1.schedulePendingIntent(r2, r3, r4, r5);
        return;
    L_0x003d:
        r0 = r7.getHourFormat();
        r1 = r9.getTime();
        r0 = r0.format(r1);
        r2 = java.lang.Integer.parseInt(r0);
        r0 = r7.getMinuteFormat();
        r9 = r9.getTime();
        r9 = r0.format(r9);
        r3 = java.lang.Integer.parseInt(r9);
        r4 = 1;
        goto L_0x0035;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.outthinking.abs.alarm.alarmmanagerdemo.Ex_NotificationPublisher.setTimeHrAndMin(com.outthinking.abs.alarm.alarmmanagerdemo.Ex_AlarmHelper, java.util.Calendar):void");
    }

    public SimpleDateFormat startTimeFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        this.startTimeFormat = simpleDateFormat;
        return simpleDateFormat;
    }
}
