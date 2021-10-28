package com.example.fitandfine.Ex_alarm;


import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;

import com.example.fitandfine.Ex_activities.MainActivity;


public class Ex_AlarmReciever extends BroadcastReceiver {

    String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub


        //Trigger the notification
        Ex_NotificationSchedular.showNotification(context, MainActivity.class,
                "It's time for exercise", "Start now?");

    }
}
