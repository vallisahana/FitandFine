package com.example.fitandfine.Ex_alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import androidx.core.app.NotificationCompat;
import android.util.Log;
//import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.example.fitandfine.Ex_AbsBootReceiver;

import java.util.Calendar;

public class Ex_AlarmHelper {
    private static final int REQUEST_CODE = 1234;
    private static final String TAG = "Ex_AlarmMainActivity";
    private AlarmManager alarmManager;
    private Context context;

    public Ex_AlarmHelper(Context context) {
        this.context = context;
        this.alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    private PendingIntent getPendingIntent() {
        Intent intent = new Intent("com.outthinking.abs.alarmmanagerdemo.NOTIFY_ACTION");
        intent.setClass(this.context, Ex_NotificationPublisher.class);
   //     intent.setFlags(ErrorDialogData.BINDER_CRASH);
        return PendingIntent.getBroadcast(this.context, REQUEST_CODE, intent, 134217728);
    }

    private void setBootReceiverEnabled(int i) {
        Log.d(TAG, "setBootReceiverEnabled: ");
        this.context.getPackageManager().setComponentEnabledSetting(new ComponentName(this.context, Ex_AbsBootReceiver.class), i, 1);
    }

    public boolean isAlarmScheduled() {
        Intent intent = new Intent("com.outthinking.abs.alarmmanagerdemo.NOTIFY_ACTION");
        intent.setClass(this.context, Ex_NotificationPublisher.class);
        return PendingIntent.getBroadcast(this.context, REQUEST_CODE, intent, 0) != null;
    }

    public void schedulePendingIntent(int i, int i2, int i3) {
        Calendar instance = Calendar.getInstance();
        instance.set(11, i);
        instance.set(12, i2);
        instance.set(13, 0);
        instance.set(14, 0);
        instance.set(9, i3);
        schedulePendingIntent(instance.getTimeInMillis(), getPendingIntent());
    }

    public void schedulePendingIntent(int i, int i2, int i3, long j) {
        Calendar instance = Calendar.getInstance();
        instance.set(11, i);
        instance.set(12, i2);
        instance.set(13, 0);
        instance.set(14, 0);
        instance.set(9, i3);
        schedulePendingIntent(instance.getTimeInMillis(), getPendingIntent(), j);
    }

    public void schedulePendingIntent(long j, PendingIntent pendingIntent) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("schedulePendingIntent: ");
        stringBuilder.append(j);
        stringBuilder.append("/");
        stringBuilder.append(pendingIntent);
        Log.d(str, stringBuilder.toString());
        if (VERSION.SDK_INT >= 23) {
            Log.d(TAG, "setExactAndAllowWhileIdle");
            this.alarmManager.setExactAndAllowWhileIdle(0, j, pendingIntent);
        } else if (VERSION.SDK_INT >= 19) {
            Log.d(TAG, "setExact");
            this.alarmManager.setExact(0, j, pendingIntent);
        } else {
            Log.d(TAG, "set");
            this.alarmManager.set(0, j, pendingIntent);
        }
        setBootReceiverEnabled(1);
    }

    public void schedulePendingIntent(long j, PendingIntent pendingIntent, long j2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("schedulePendingIntent: ");
        stringBuilder.append(j);
        stringBuilder.append("/");
        stringBuilder.append(pendingIntent);
        Log.d(str, stringBuilder.toString());
        if (VERSION.SDK_INT >= 23) {
            Log.d(TAG, "setExactAndAllowWhileIdle");
            this.alarmManager.setExactAndAllowWhileIdle(0, j + j2, pendingIntent);
        } else if (VERSION.SDK_INT >= 19) {
            Log.d(TAG, "setExact");
            this.alarmManager.setExact(0, j + j2, pendingIntent);
        } else {
            Log.d(TAG, "set");
            this.alarmManager.set(0, j + j2, pendingIntent);
        }
        setBootReceiverEnabled(1);
    }

    public void unschedulePendingIntent() {
        PendingIntent pendingIntent = getPendingIntent();
        pendingIntent.cancel();
        this.alarmManager.cancel(pendingIntent);
    }
}
