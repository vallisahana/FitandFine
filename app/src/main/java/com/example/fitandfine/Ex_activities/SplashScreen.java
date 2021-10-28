package com.example.fitandfine.Ex_activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.fitandfine.R;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 5000;


    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.exec_splash_screen);
        YoYo.with(Techniques.Bounce)
                .duration(7000)
                .playOn(findViewById(R.id.logo));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent registerintent = new Intent(SplashScreen.this,Welcome.class);
                startActivity(registerintent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
