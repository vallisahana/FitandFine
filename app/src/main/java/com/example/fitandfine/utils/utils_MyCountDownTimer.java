package com.example.fitandfine.utils;

import android.os.Handler;
import androidx.core.app.NotificationCompat;
import android.util.Log;

class utils_MyCountDownTimer {
    private long countDownInterval;
    private long millisInFuture;

    public utils_MyCountDownTimer(long j, long j2) {
        this.millisInFuture = j;
        this.countDownInterval = j2;
    }

    public void Start() {
        final Handler handler = new Handler();
        Log.v(NotificationCompat.CATEGORY_STATUS, "starting");
        handler.postDelayed(new Runnable() {
            public void run() {
                if (utils_MyCountDownTimer.this.millisInFuture <= 0) {
                    Log.v(NotificationCompat.CATEGORY_STATUS, "done");
                    return;
                }
                long access$000 = utils_MyCountDownTimer.this.millisInFuture / 1000;
                String str = NotificationCompat.CATEGORY_STATUS;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(Long.toString(access$000));
                stringBuilder.append(" seconds remain");
                Log.v(str, stringBuilder.toString());
                utils_MyCountDownTimer.this.millisInFuture = utils_MyCountDownTimer.this.millisInFuture - utils_MyCountDownTimer.this.countDownInterval;
                handler.postDelayed(this, utils_MyCountDownTimer.this.countDownInterval);
            }
        }, this.countDownInterval);
    }
}
