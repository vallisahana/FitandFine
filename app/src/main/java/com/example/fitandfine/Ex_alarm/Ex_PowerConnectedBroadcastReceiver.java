package com.example.fitandfine.Ex_alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Ex_PowerConnectedBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "PowerConnectedBroRe";

    public void onReceive(Context context, Intent intent) {
        Ex_AlarmHelper exAlarmHelper = new Ex_AlarmHelper(context);
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PendingIntent.getBroadcast(...) != null: ");
        stringBuilder.append(exAlarmHelper.isAlarmScheduled());
        Log.d(str, stringBuilder.toString());
    }
}
