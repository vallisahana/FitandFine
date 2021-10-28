package com.example.fitandfine.utils;

import android.app.Application;
import android.os.Build.VERSION;
import android.speech.tts.TextToSpeech;
import com.example.fitandfine.R;
import com.example.fitandfine.ThrowableExtension;

import java.util.Locale;

public class Excercise_AbsWomenApplication extends Application {
    private static Excercise_AbsWomenApplication excerciseAbsWomenApplication;
    public TextToSpeech textToSpeech;

    public static Excercise_AbsWomenApplication getInstance() {
        return excerciseAbsWomenApplication;
    }

    public void addEarCorn() {
        try {
            if (this.textToSpeech != null) {
                this.textToSpeech.addEarcon("tick", getPackageName(), R.raw.ticktok);
            }
        } catch (Throwable e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    final /* synthetic */ void lambda$null$0$AbsWomenApplication(int i) {
        if (i == 0) {
            this.textToSpeech.setLanguage(Locale.US);
        }
    }

    final /* synthetic */ void lambda$onCreate$1$AbsWomenApplication() {
        if (this.textToSpeech == null) {
            this.textToSpeech = new TextToSpeech(getInstance(), new Excercise_AbsWomenApplication_setLanguage_onClick(this));
        }
    }

    public void onCreate() {
        super.onCreate();
        excerciseAbsWomenApplication = this;
        new Thread(new Excercise_AbsWomenApplication_setLanguage(this)).start();
    }

    public void playEarCorn() {
        try {
            if (this.textToSpeech != null) {
                if (VERSION.SDK_INT >= 21) {
                    this.textToSpeech.playEarcon("tick", 0, null, getPackageName());
                } else {
                    this.textToSpeech.playEarcon("tick", 0, null);
                }
            }
        } catch (Throwable e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void shutdown() {
        try {
            if (this.textToSpeech != null) {
                this.textToSpeech.shutdown();
            }
        } catch (Throwable e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void speak(String str) {
        try {
            if (this.textToSpeech != null) {
                this.textToSpeech.setSpeechRate(1.0f);
                this.textToSpeech.speak(str, 1, null);
            }
        } catch (Throwable e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void stop() {
        try {
            if (this.textToSpeech != null) {
                this.textToSpeech.stop();
            }
        } catch (Throwable e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}
