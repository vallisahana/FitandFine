package com.example.fitandfine.Ex_activities;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import com.example.fitandfine.Ex_adapters.Ex_WorkoutData;
import com.example.fitandfine.Ex_database.Ex_DatabaseOperations;
import com.example.fitandfine.ThrowableExtension;
import com.example.fitandfine.utils.Excercise_AbsWomenApplication;
import com.example.fitandfine.utils.utils_AppUtils;
import com.example.fitandfine.utils.utils_Constants;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;

import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.example.fitandfine.R;

import java.util.ArrayList;
import kr.pe.burt.android.lib.faimageview.FAImageView;

public class MainExcerciseActivity extends AppCompatActivity {
    long REST_TIME_IN_MS = 25000;
    private Excercise_AbsWomenApplication absWomenApplication;
//    private AdChoicesView adChoicesView;
    private AdRequest adRequest;
    private AdRequest adRequest1;
    private LinearLayout adView;
    private AdView mAdView,mAdView1,mAdView2;
    private FAImageView animImageFull;
    private Context context;
    private TextView count;
    private TextView countRestTimer;
    private Ex_DatabaseOperations databaseOperations;
    private String day;
    private TextView eachSideTextView;
    private int excCouner;
    private TextView excDescInReadyToGo;
    long excDurGlobal;
    private TextView excName;
    private TextView excNameInReadyToGo;
    private CountDownTimer excersiseTimer;
    /* renamed from: i */
    private int f4608i = 0;
    private LayoutInflater inflater;
    private LinearLayout layoutprogress;
    private int mainExcCounter = 1;
    float mainExcProgress = 1.0f;
    //private NativeAd nativeAd;
    private LinearLayout nativeAdContainer;
    private FAImageView nextExerciseAnimation;
    private TextView nextExerciseCycles;
    private TextView nextExerciseName;
    private ImageView pauseMainExcersise;
    private TextView pauseRestTime;
    private FloatingActionButton playPause;
    private double progress;
    private RoundCornerProgressBar progressbar;
    private CountDownTimer readyToGoTimer;
    private CoordinatorLayout readytogo_layout;
    private RelativeLayout restScreen;
    private CountDownTimer restTimer;
    private ProgressBar restTimerprogress;
    private TextView resumRestTime;
    private ImageView resumeMainExcersise;
    private long s1;
    private int screenheight;
    private TextView timerTop;
    private ProgressBar timerprogress;
    private ProgressBar topProgressBar;
    private TextView tvProgress;
    private TextView tvProgressMax;
  //  private ArrayList<Ex_WorkoutData> exWorkoutDataList;
    private ArrayList<Ex_WorkoutData> exWorkoutDataList;

    /* renamed from: com.neontechs.outhinkingabs.activities.MainExcerciseActivity$1 */
    class C08551 implements OnClickListener {
        C08551() {
        }

        public void onClick(View view) {
            MainExcerciseActivity.this.restTimer.cancel();
            MainExcerciseActivity.this.restTimer.onFinish();
            MainExcerciseActivity.this.pauseRestTime.setVisibility(View.VISIBLE);
            MainExcerciseActivity.this.resumRestTime.setVisibility(View.GONE);
        }
    }

    /* renamed from: com.neontechs.outhinkingabs.activities.MainExcerciseActivity$6 */
    class C12646 extends AdListener {
        C12646() {
        }

        public void onAdClosed() {
            super.onAdClosed();

        }
    }

    /* renamed from: com.neontechs.outhinkingabs.activities.MainExcerciseActivity$7 */
    class C12657 extends AdListener {
        C12657() {
        }

        public void onAdClosed() {
            super.onAdClosed();

        }
    }

    public static int dpToPx() {
        return (int) (Resources.getSystem().getDisplayMetrics().density * 50.0f);
    }

    private void getScreenHeightWidth() {
        this.context = this;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.screenheight = displayMetrics.heightPixels;
    }


    private void mainExcTimer(long j, int i, float f) {
        Ex_WorkoutData workoutData = (Ex_WorkoutData) this.exWorkoutDataList.get(this.excCouner);
        this.animImageFull.reset();
        for (int addImageFrame : workoutData.getImageIdList()) {
            this.animImageFull.addImageFrame(addImageFrame);
        }
        this.restScreen.setVisibility(8);
        this.animImageFull.startAnimation();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("progressbar: ");
        stringBuilder.append(this.excDurGlobal / 1000);
        Log.i("setMax", stringBuilder.toString());
        this.progressbar.setMax((float) ((this.excDurGlobal / 1000) - 1));
        this.topProgressBar = (ProgressBar) this.layoutprogress.findViewById(this.excCouner);
        this.topProgressBar.setProgressDrawable(getResources().getDrawable(R.drawable.neo_launch_progressbar));
        this.topProgressBar.setMax((((int) this.excDurGlobal) / 1000) - 1);
        this.absWomenApplication.addEarCorn();
        stringBuilder = new StringBuilder();
        stringBuilder.append("setMax: ");
        stringBuilder.append(j / 1000);
        Log.i("mainExcTimer", stringBuilder.toString());
        final float f2 = f;
        final int i2 = i;
        this.excersiseTimer = new CountDownTimer(j, 1000) {
            int count = i2;
            /* renamed from: f */
            float f2765f = f2;

            @SuppressLint({"SetTextI18n"})
            public void onFinish() {
                RoundCornerProgressBar access$600 = MainExcerciseActivity.this.progressbar;
                float f = this.f2765f;
                this.f2765f = f + 1.0f;
                access$600.setProgress(f);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("count: ");
                stringBuilder.append(this.count);
                stringBuilder.append("f ");
                stringBuilder.append(this.f2765f);
                Log.i("onTick onFinish", stringBuilder.toString());
                MainExcerciseActivity.this.excCouner = MainExcerciseActivity.this.excCouner + 1;
                MainExcerciseActivity.this.animImageFull.stopAnimation();
                double d = 100.0d;
                int i = 0;
                Intent intent;
                if (MainExcerciseActivity.this.excCouner < MainExcerciseActivity.this.exWorkoutDataList.size()) {
                    MainExcerciseActivity.this.restScreen.setVisibility(0);


                    /*AdView mAdView;


                    mAdView = findViewById(R.id.addViewMainAct4);
                    AdRequest adRequest = new AdRequest.Builder().build();
                    mAdView.loadAd(adRequest);*/

                    MainExcerciseActivity.this.progressbar.setMax((float) ((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(MainExcerciseActivity.this.excCouner)).getExcCycles());
               //     TextView access$500 = MainExcerciseActivity.this.tvProgress;
                    stringBuilder = new StringBuilder();
                    int i2 = this.count;
                    this.count = i2 + 1;
                    stringBuilder.append(i2);
                    stringBuilder.append("");
                    tvProgress.setText(stringBuilder.toString());
                  //  access$500 = MainExcerciseActivity.this.tvProgressMax;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(MainExcerciseActivity.this.excCouner)).getExcCycles());
                    stringBuilder.append("");
                    tvProgressMax.setText(stringBuilder.toString());
                    this.f2765f = 1.0f;
                    this.count = 1;
                    MainExcerciseActivity.this.progress = 100.0d / ((double) ((float) MainExcerciseActivity.this.exWorkoutDataList.size()));
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("progress: ");
                    stringBuilder.append(MainExcerciseActivity.this.progress);
                    Log.i("MainExcerciseActivity", stringBuilder.toString());
                    float excDayProgress = MainExcerciseActivity.this.databaseOperations.getExcDayProgress(MainExcerciseActivity.this.day);
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("prog from db: ");
                    stringBuilder2.append(excDayProgress);
                    Log.i("MainExcerciseActivity", stringBuilder2.toString());
                    MainExcerciseActivity.this.progress = ((double) excDayProgress) + MainExcerciseActivity.this.progress;
                    if (MainExcerciseActivity.this.progress <= 100.0d) {
                        MainExcerciseActivity.this.databaseOperations.insertExcDayData(MainExcerciseActivity.this.day, (float) MainExcerciseActivity.this.progress);
                    }
                    if (MainExcerciseActivity.this.excCouner <= MainExcerciseActivity.this.exWorkoutDataList.size()) {
                        MainExcerciseActivity.this.databaseOperations.insertExcCounter(MainExcerciseActivity.this.day, MainExcerciseActivity.this.excCouner);
                    }
                    try {

                        intent = new Intent(utils_AppUtils.WORKOUT_BROADCAST_FILTER);
                        intent.putExtra(utils_AppUtils.KEY_PROGRESS, MainExcerciseActivity.this.progress);
                        MainExcerciseActivity.this.sendBroadcast(intent);
                    } catch (Throwable e) {
                        ThrowableExtension.printStackTrace(e);
                        Log.e("Failed update progress", e.getMessage());
                    }
                    MainExcerciseActivity.this.pauseRestTime.setVisibility(View.VISIBLE);
                    MainExcerciseActivity.this.resumRestTime.setVisibility(View.GONE);
                    MainExcerciseActivity.this.restTimerFun(MainExcerciseActivity.this.REST_TIME_IN_MS);
                } else {
                    Ex_DatabaseOperations access$1900 = null;
                    String access$1800=null;
                    float access$1700=0;
                    int i3;
                    int i4;
                    MainExcerciseActivity.this.progress = 100.0d / ((double) ((float) MainExcerciseActivity.this.exWorkoutDataList.size()));
                    MainExcerciseActivity.this.progress = ((double) MainExcerciseActivity.this.databaseOperations.getExcDayProgress(MainExcerciseActivity.this.day)) + MainExcerciseActivity.this.progress;
                    if (((int) MainExcerciseActivity.this.progress) > 100 || MainExcerciseActivity.this.progress < 98.0d) {
                        if (((int) MainExcerciseActivity.this.progress) <= 100) {
                            access$1900 = MainExcerciseActivity.this.databaseOperations;
                            access$1800 = MainExcerciseActivity.this.day;
                            access$1700 = (float) MainExcerciseActivity.this.progress;
                        }
                        if (MainExcerciseActivity.this.excCouner <= MainExcerciseActivity.this.exWorkoutDataList.size()) {
                            MainExcerciseActivity.this.databaseOperations.insertExcCounter(MainExcerciseActivity.this.day, MainExcerciseActivity.this.excCouner);
                        }
                        intent = new Intent(utils_AppUtils.WORKOUT_BROADCAST_FILTER);
                        if (MainExcerciseActivity.this.progress < 98.0d) {
                            access$1800 = utils_AppUtils.KEY_PROGRESS;
                        } else {
                            access$1800 = utils_AppUtils.KEY_PROGRESS;
                            d = MainExcerciseActivity.this.progress;
                        }
                        intent.putExtra(access$1800, d);
                        MainExcerciseActivity.this.sendBroadcast(intent);
                        MainExcerciseActivity.this.restScreen.setVisibility(8);
                        intent = new Intent(MainExcerciseActivity.this, Ex_CompletionExcActivity.class);
                        i3 = 0;
                        i4 = 0;
                        while (i < MainExcerciseActivity.this.exWorkoutDataList.size()) {
                            i3 += ((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(i)).getExcCycles();
                            i4 = (i4 + ((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(i)).getImageIdList().length) + utils_Constants.REST_TIME;
                            i++;
                        }
                        intent.putExtra("totalExc", i3);
                        intent.putExtra("totalTime", i4);
                        MainExcerciseActivity.this.startActivity(intent);
                    } else {
                        access$1900 = MainExcerciseActivity.this.databaseOperations;
                        access$1800 = MainExcerciseActivity.this.day;
                        access$1700 = 100.0f;
                    }
                    access$1900.insertExcDayData(access$1800, access$1700);
                    if (MainExcerciseActivity.this.excCouner <= MainExcerciseActivity.this.exWorkoutDataList.size()) {
                        MainExcerciseActivity.this.databaseOperations.insertExcCounter(MainExcerciseActivity.this.day, MainExcerciseActivity.this.excCouner);
                    }
                    try {
                        intent = new Intent(utils_AppUtils.WORKOUT_BROADCAST_FILTER);
                        if (MainExcerciseActivity.this.progress < 98.0d) {
                            access$1800 = utils_AppUtils.KEY_PROGRESS;
                            d = MainExcerciseActivity.this.progress;
                        } else {
                            access$1800 = utils_AppUtils.KEY_PROGRESS;
                        }
                        intent.putExtra(access$1800, d);
                        MainExcerciseActivity.this.sendBroadcast(intent);
                    } catch (Throwable e2) {
                        ThrowableExtension.printStackTrace(e2);
                    }
                    MainExcerciseActivity.this.restScreen.setVisibility(View.GONE);
                    intent = new Intent(MainExcerciseActivity.this, Ex_CompletionExcActivity.class);
                    i3 = 0;
                    i4 = 0;
                    while (i < MainExcerciseActivity.this.exWorkoutDataList.size()) {
                        i3 += ((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(i)).getExcCycles();
                        i4 = (i4 + ((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(i)).getImageIdList().length) + utils_Constants.REST_TIME;
                        i++;
                    }
                    intent.putExtra("totalExc", i3);
                    intent.putExtra("totalTime", i4);
                    MainExcerciseActivity.this.startActivity(intent);

                }
                stringBuilder = new StringBuilder();
                stringBuilder.append("excCouner: ");
                stringBuilder.append(MainExcerciseActivity.this.excCouner);
                Log.i("MainExcerciseActivity", stringBuilder.toString());
                MainExcerciseActivity.this.mainExcProgress = 1.0f;
                MainExcerciseActivity.this.mainExcCounter = 1;
            }

            public void onTick(long j) {
                TextView access$500;
                StringBuilder stringBuilder = new StringBuilder();
                Object obj;
                RoundCornerProgressBar access$600;
                float f;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("millisUntilFinished: ");
                stringBuilder2.append(j);
                Log.i("millisUntilFinished", stringBuilder2.toString());
                if (((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(MainExcerciseActivity.this.excCouner)).getImageIdList().length <= 2) {
                    access$500 = MainExcerciseActivity.this.tvProgress;
                    stringBuilder = new StringBuilder();
                } else if (this.f2765f == 1.0f) {
                   // Toast.makeText(MainExcerciseActivity.this, "else if", Toast.LENGTH_SHORT).show();
                    access$500 = MainExcerciseActivity.this.tvProgress;
                    obj = "1";
                    access$500.setText(String.valueOf(obj));
                    access$600 = MainExcerciseActivity.this.progressbar;
                    f = this.f2765f;
                    this.f2765f = 1.0f + f;
                    access$600.setProgress(f);
                    MainExcerciseActivity.this.topProgressBar.setProgress((int) this.f2765f);
                    access$500 = MainExcerciseActivity.this.timerTop;
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(this.count);
                    stringBuilder2.append("");
                    access$500.setText(stringBuilder2.toString());
                    MainExcerciseActivity.this.excName.setText(((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(MainExcerciseActivity.this.excCouner)).getExcName().replace("_", " ").toUpperCase());
                    access$500 = MainExcerciseActivity.this.eachSideTextView;
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Each Side x ");
                    stringBuilder2.append(((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(MainExcerciseActivity.this.excCouner)).getExcCycles() / 2);
                    access$500.setText(stringBuilder2.toString());
                    access$500 = MainExcerciseActivity.this.tvProgressMax;
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("/");
                    stringBuilder2.append(((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(MainExcerciseActivity.this.excCouner)).getExcCycles());
                    access$500.setText(stringBuilder2.toString());
                    MainExcerciseActivity.this.s1 = j;
                    MainExcerciseActivity.this.mainExcCounter = this.count;
                    MainExcerciseActivity.this.mainExcProgress = this.f2765f;
                    MainExcerciseActivity.this.absWomenApplication.playEarCorn();
                } else {
                 //   Toast.makeText(MainExcerciseActivity.this, "else", Toast.LENGTH_SHORT).show();
                    if (this.f2765f % ((float) ((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(MainExcerciseActivity.this.excCouner)).getImageIdList().length) == 0.0f) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("millisUntilFinished: ");
                        stringBuilder.append(this.f2765f % ((float) ((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(MainExcerciseActivity.this.excCouner)).getImageIdList().length));
                        Log.i("mainExce", stringBuilder.toString());
                        access$500 = MainExcerciseActivity.this.tvProgress;
                        stringBuilder = new StringBuilder();
                    }
                    access$600 = MainExcerciseActivity.this.progressbar;
                    f = this.f2765f;
                   // Toast.makeText(MainExcerciseActivity.this, String.valueOf(f), Toast.LENGTH_SHORT).show();

                    this.f2765f =  f;
                    access$600.setProgress(f);
                    MainExcerciseActivity.this.topProgressBar.setProgress((int) this.f2765f);
                    access$500 = MainExcerciseActivity.this.timerTop;
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(this.count);
                    stringBuilder2.append("");
                    access$500.setText(stringBuilder2.toString());
                  //  Toast.makeText(MainExcerciseActivity.this, stringBuilder2.toString(), Toast.LENGTH_SHORT).show();

                    MainExcerciseActivity.this.excName.setText(((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(MainExcerciseActivity.this.excCouner)).getExcName().replace("_", " ").toUpperCase());
                    access$500 = MainExcerciseActivity.this.eachSideTextView;
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Each Side x ");
                    stringBuilder2.append(((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(MainExcerciseActivity.this.excCouner)).getExcCycles() / 2);
                    access$500.setText(stringBuilder2.toString());
                    access$500 = MainExcerciseActivity.this.tvProgressMax;
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("/");
                    stringBuilder2.append(((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(MainExcerciseActivity.this.excCouner)).getExcCycles());
                    access$500.setText(stringBuilder2.toString());
                    MainExcerciseActivity.this.s1 = j;
                    MainExcerciseActivity.this.mainExcCounter = this.count;
                    MainExcerciseActivity.this.mainExcProgress = this.f2765f;
                    MainExcerciseActivity.this.absWomenApplication.playEarCorn();
                }
                int i = this.count;
                this.count = i + 1;
                stringBuilder.append(i);
                stringBuilder.append("");
                obj = stringBuilder.toString();
                tvProgress.setText("Step No :"+String.valueOf(obj));
                access$600 = MainExcerciseActivity.this.progressbar;
                f = this.f2765f;
                this.f2765f = 1.0f + f;
                access$600.setProgress(f);
                MainExcerciseActivity.this.topProgressBar.setProgress((int) this.f2765f);
                access$500 = MainExcerciseActivity.this.timerTop;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(this.count);
                stringBuilder2.append("");
                access$500.setText(stringBuilder2.toString());
                MainExcerciseActivity.this.excName.setText(((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(MainExcerciseActivity.this.excCouner)).getExcName().replace("_", " ").toUpperCase());
                access$500 = MainExcerciseActivity.this.eachSideTextView;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Each Side x ");
                stringBuilder2.append(((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(MainExcerciseActivity.this.excCouner)).getExcCycles() / 2);
                access$500.setText(stringBuilder2.toString());
                access$500 = MainExcerciseActivity.this.tvProgressMax;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("/");
                stringBuilder2.append(((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(MainExcerciseActivity.this.excCouner)).getExcCycles());
                access$500.setText(stringBuilder2.toString());
                MainExcerciseActivity.this.s1 = j;
                MainExcerciseActivity.this.mainExcCounter = this.count;
                MainExcerciseActivity.this.mainExcProgress = this.f2765f;
                MainExcerciseActivity.this.absWomenApplication.playEarCorn();
            }
        }.start();
    }





    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    void displayNativeAd() {
        this.nativeAdContainer = (LinearLayout) findViewById(R.id.nativeAdContainer);
        ViewGroup.LayoutParams layoutParams = this.nativeAdContainer.getLayoutParams();
        int dpToPx = dpToPx();
        layoutParams.height = (((this.screenheight / 3) + dpToPx) + (dpToPx / 2)) + 15;
        this.inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
     //   this.adChoicesView = null;
        this.nativeAdContainer.setBackgroundResource(0);
       // showNativeAd();
    }

    void exitConfirmDialog() {
        new MaterialDialog.Builder(this).title((CharSequence) "Confirm!").titleColor(ContextCompat.getColor(this, R.color.textColor)).content((CharSequence) "Would you like to quit the workout?").contentColor(ContextCompat.getColor(this, R.color.textColor)).positiveText((CharSequence) "Yes").positiveColor(ContextCompat.getColor(this, R.color.activeColor)).onPositive(new MainExcerciseActivity_exitConfirmDialog_Yes(this)).negativeText((CharSequence) "No").negativeColor(ContextCompat.getColor(this, R.color.activeColor)).onNegative((MainExcerciseActivity_exitConfirmDialog_No.$instance)).show();
    }

    final /* synthetic */ void lambda$exitConfirmDialog$7$MainExcerciseActivity(MaterialDialog materialDialog, DialogAction dialogAction) {
        try {
            materialDialog.dismiss();
            if (this.readyToGoTimer != null) {
                this.readyToGoTimer.cancel();
            }
            if (this.excersiseTimer != null) {
                this.excersiseTimer.cancel();
            }
            if (this.restTimer != null) {
                this.restTimer.cancel();
            }
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(603979776);
            startActivity(intent);
            onSuperBackPressed();
        } catch (Throwable e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
    final /* synthetic */ void lambda$onCreate$0$MainExcerciseActivity(View view) {
        this.readyToGoTimer.cancel();
        this.readyToGoTimer.onFinish();
    }

    final /* synthetic */ void lambda$onCreate$1$MainExcerciseActivity(View view) {
        finish();
    }

    final /* synthetic */ void lambda$onCreate$2$MainExcerciseActivity(View view) {
        if (this.f4608i % 2 == 0) {
            this.playPause.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
            this.readyToGoTimer.cancel();
        } else {
            this.playPause.setImageResource(R.drawable.neo_ic_pause_black_24dp);
            readyToGoFun(this.s1);
        }
        this.f4608i++;
    }

    final /* synthetic */ void lambda$onCreate$3$MainExcerciseActivity(View view) {
        this.pauseRestTime.setVisibility(View.GONE);
        this.resumRestTime.setVisibility(View.VISIBLE);
        this.restTimer.cancel();
    }

    final /* synthetic */ void lambda$onCreate$4$MainExcerciseActivity(View view) {
        this.pauseRestTime.setVisibility(View.VISIBLE);
        this.resumRestTime.setVisibility(View.GONE);
        restTimerFun(this.s1);
    }

    final /* synthetic */ void lambda$onCreate$5$MainExcerciseActivity(View view) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mainExcCounter");
        stringBuilder.append(this.mainExcCounter);
        stringBuilder.append("mainExcProgress ");
        stringBuilder.append(this.mainExcProgress);
        Log.i("pauseMainExcersise", stringBuilder.toString());
        this.pauseMainExcersise.setVisibility(View.GONE);
        this.resumeMainExcersise.setVisibility(View.VISIBLE);
        this.excersiseTimer.cancel();
        this.animImageFull.stopAnimation();
    }

    final /* synthetic */ void lambda$onCreate$6$MainExcerciseActivity(View view) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mainExcCounter");
        stringBuilder.append(this.mainExcCounter);
        stringBuilder.append("mainExcProgress ");
        stringBuilder.append(this.mainExcProgress);
        Log.i("resumeMainExcersise", stringBuilder.toString());
        this.pauseMainExcersise.setVisibility(View.VISIBLE);
        this.resumeMainExcersise.setVisibility(View.GONE);
        mainExcTimer(this.s1 - 1000, this.mainExcCounter, this.mainExcProgress);
    }

    public void onBackPressed() {
        exitConfirmDialog();
    }
    private void populateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {
        // Set the media view. Media content will be automatically populated in the media view once
        // adView.setNativeAd() is called.
        MediaView mediaView = adView.findViewById(R.id.ad_media);
        adView.setMediaView(mediaView);

        // Set other ad assets.
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        // The headline is guaranteed to be in every UnifiedNativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad. The SDK will populate the adView's MediaView
        // with the media content from this native ad.
        adView.setNativeAd(nativeAd);

        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
        // have a video asset.
        VideoController vc = nativeAd.getVideoController();

        // Updates the UI to say whether or not this ad has a video asset.
        if (vc.hasVideoContent()) {

            vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                @Override
                public void onVideoEnd() {
                    // Publishers should allow native ads to complete video playback before
                    // refreshing or replacing them with another ad in the same UI location.
                    //    refresh.setEnabled(true);
                    //    videoStatus.setText("Video status: Video playback has ended.");
                    super.onVideoEnd();
                }
            });
        } else {
            // videoStatus.setText("Video status: Ad does not contain a video asset.");
            // refresh.setEnabled(true);
        }
    }

    /**
     * Creates a request for a new native ad based on the boolean parameters and calls the
     * corresponding "populate" method when one is successfully returned.
     *
     */




    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getWindow().addFlags(128);
        setContentView((int) R.layout.exec_mainexcercise_layout);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
    //    mAdView = findViewById(R.id.adView);
        try {
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
     //   mAdView1 = findViewById(R.id.adView1);
        try {
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView1.loadAd(adRequest);
        }catch (Exception e){
            e.printStackTrace();
        }// mAdView2 = findViewById(R.id.adView2);
        try {
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView2.loadAd(adRequest);
        }catch (Exception e){
            e.printStackTrace();
        }

        this.databaseOperations = new Ex_DatabaseOperations(this);
        this.exWorkoutDataList = (ArrayList) getIntent().getExtras().getSerializable("exWorkoutDataList");
        Bundle extras = getIntent().getExtras();
        extras.getClass();

        this.day = extras.getString("day");
        getIntent().getExtras().getFloat(NotificationCompat.CATEGORY_PROGRESS);
        this.absWomenApplication = Excercise_AbsWomenApplication.getInstance();
        this.excCouner = this.databaseOperations.getDayExcCounter(this.day);
        this.progressbar = (RoundCornerProgressBar) findViewById(R.id.progress_one);
        this.animImageFull = (FAImageView) findViewById(R.id.animImageFull);
        this.tvProgress = (TextView) findViewById(R.id.tv_progress);
        this.tvProgressMax = (TextView) findViewById(R.id.tv_progress_max);
        this.timerTop = (TextView) findViewById(R.id.timerTop);
        this.restScreen = (RelativeLayout) findViewById(R.id.restScreen);
        this.excName = (TextView) findViewById(R.id.excName);
        this.pauseMainExcersise = (ImageView) findViewById(R.id.pauseMainExcersise);
        this.resumeMainExcersise = (ImageView) findViewById(R.id.resumeMainExcersise);
        TextView textView = (TextView) findViewById(R.id.skip);
        this.timerprogress = (ProgressBar) findViewById(R.id.timer);
        ImageView imageView = (ImageView) findViewById(R.id.backImage);
        this.count = (TextView) findViewById(R.id.counting);
        this.playPause = (FloatingActionButton) findViewById(R.id.floatingPlay);
        this.eachSideTextView = (TextView) findViewById(R.id.eachSideTextView);
        this.excDescInReadyToGo = (TextView) findViewById(R.id.excDescInReadyToGo);
        this.excNameInReadyToGo = (TextView) findViewById(R.id.excNameInReadyToGo);
        this.pauseRestTime = (TextView) findViewById(R.id.pauseRestTime);
        this.resumRestTime = (TextView) findViewById(R.id.resumeRestTime);
        this.restTimerprogress = (ProgressBar) findViewById(R.id.rest_timer);
        this.countRestTimer = (TextView) findViewById(R.id.rest_counting);
        this.nextExerciseName = (TextView) findViewById(R.id.nextExerciseName);
        this.nextExerciseCycles = (TextView) findViewById(R.id.nextExerciseCycles);
        this.nextExerciseAnimation = (FAImageView) findViewById(R.id.nextExercisAnimation);
        this.layoutprogress = (LinearLayout) findViewById(R.id.hLayoutprogress);
        this.readytogo_layout = (CoordinatorLayout) findViewById(R.id.readytogo_layout);
        this.progressbar.setMax(10.0f);
        this.animImageFull.setInterval(1000);
        this.animImageFull.setLoop(true);
        this.animImageFull.reset();
        int i = 0;
        for (int addImageFrame : ((Ex_WorkoutData) this.exWorkoutDataList.get(this.excCouner)).getImageIdList()) {
            this.animImageFull.addImageFrame(addImageFrame);
        }
        this.animImageFull.startAnimation();
        findViewById(R.id.skipRestTime).setOnClickListener(new C08551());
        textView.setOnClickListener(new MainExcerciseActivity_Click(this));
        imageView.setOnClickListener(new MainExcerciseActivity_imageView_Click(this));
        this.playPause.setOnClickListener(new MainExcerciseActivity_playPause_Click(this));
        this.pauseRestTime.setOnClickListener(new MainExcerciseActivity_pauseRestTime_Click(this));
        this.resumRestTime.setOnClickListener(new MainExcerciseActivity_resumRestTime_Click(this));
        this.pauseMainExcersise.setOnClickListener(new MainExcerciseActivity_pauseMainExcersise_Click(this));
        this.resumeMainExcersise.setOnClickListener(new MainExcerciseActivity_resumeMainExcersise_Click(this));
        readyToGoFun(10000);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2, 1.0f);
        for (int i2 = 0; i2 < this.exWorkoutDataList.size(); i2++) {
            this.topProgressBar = new ProgressBar(this, null, 16842872);
          //  layoutParams.rightMargin = 2;
            //layoutParams.leftMargin = 2;
            this.topProgressBar.setPadding(0, 0, 0, -8);
            this.topProgressBar.setLayoutParams(layoutParams);
            this.topProgressBar.setId(i2);
            this.topProgressBar.setScaleY(2.5f);
            this.layoutprogress.addView(this.topProgressBar);
        }
        while (i < this.excCouner) {
            this.topProgressBar = (ProgressBar) this.layoutprogress.findViewById(i);
            this.topProgressBar.setProgressDrawable(getResources().getDrawable(R.drawable.neo_launch_progressbar));
            this.topProgressBar.setMax(((Ex_WorkoutData) this.exWorkoutDataList.get(this.excCouner)).getImageIdList().length * ((Ex_WorkoutData) this.exWorkoutDataList.get(this.excCouner)).getExcCycles());
            this.topProgressBar.setProgress(((Ex_WorkoutData) this.exWorkoutDataList.get(this.excCouner)).getImageIdList().length * ((Ex_WorkoutData) this.exWorkoutDataList.get(this.excCouner)).getExcCycles());
            i++;
        }
        getScreenHeightWidth();
        //displayNativeAd();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    protected void onPause() {
        super.onPause();
        if (this.readyToGoTimer != null) {
            this.readyToGoTimer.cancel();
        }
        if (this.excersiseTimer != null) {
            this.excersiseTimer.cancel();
        }
        if (this.restTimer != null) {
            this.restTimer.cancel();
        }
        this.f4608i--;
        this.playPause.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
        this.resumeMainExcersise.setVisibility(View.VISIBLE);
        this.pauseMainExcersise.setVisibility(View.GONE);
        this.resumRestTime.setVisibility(View.VISIBLE);
        this.pauseRestTime.setVisibility(View.GONE);
        this.animImageFull.stopAnimation();
    }

    protected void onResume() {
        super.onResume();
        this.pauseMainExcersise.setVisibility(View.GONE);
        this.resumeMainExcersise.setVisibility(View.VISIBLE);
    }

    public void onSuperBackPressed() {
        super.onBackPressed();
    }

    ArrayList<Ex_WorkoutData> prepareAdapterData(int[] iArr) {
        ArrayList<Ex_WorkoutData> arrayList = new ArrayList();
        for (int i = 0; i < iArr.length; i++) {
            TypedArray obtainTypedArray = getResources().obtainTypedArray(iArr[i]);
            int length = obtainTypedArray.length();
            int[] iArr2 = new int[length];
            Ex_WorkoutData workoutData = new Ex_WorkoutData();
            for (int i2 = 0; i2 < length; i2++) {
                iArr2[i2] = obtainTypedArray.getResourceId(i2, -1);
            }
            workoutData.setPosition(i);
            workoutData.setImageIdList(iArr2);
            arrayList.add(workoutData);
        }
        return arrayList;
    }

    public void readyToGoFun(long j) {
        this.excDescInReadyToGo.setText(((Ex_WorkoutData) this.exWorkoutDataList.get(this.excCouner)).getExcDescResId());
        Object toUpperCase = ((Ex_WorkoutData) this.exWorkoutDataList.get(this.excCouner)).getExcName().replace("_", " ").toUpperCase();
        this.excNameInReadyToGo.setText(toUpperCase.toString());
       // String toLowerCase = toUpperCase.toLowerCase();
        String toLowerCase = toUpperCase.toString().toLowerCase();
        if (j == 10000) {
            this.absWomenApplication.speak("ready to go ");
            Excercise_AbsWomenApplication absWomenApplication = this.absWomenApplication;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("the next exercise is ");
            stringBuilder.append(toLowerCase);
            absWomenApplication.speak(stringBuilder.toString());
        }
        this.timerprogress.setMax(10);
        this.readyToGoTimer = new CountDownTimer(j, 1000) {
            public void onFinish() {
                Log.i("readyToGoTimer", "onFinish: ");
                MainExcerciseActivity.this.timerprogress.setProgress(0);
                MainExcerciseActivity.this.readytogo_layout.setVisibility(View.GONE);
                long length = (long) (((((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.
                        get(MainExcerciseActivity.this.excCouner)).getImageIdList().length > 2 ? ((Ex_WorkoutData)
                        MainExcerciseActivity.this.exWorkoutDataList.get(MainExcerciseActivity.this.excCouner))
                        .getImageIdList().length * ((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList
                        .get(MainExcerciseActivity.this.excCouner)).getExcCycles() : ((Ex_WorkoutData)
                        MainExcerciseActivity.this.exWorkoutDataList.get(MainExcerciseActivity.this.excCouner))
                        .getExcCycles()) + 1) * 1000);
                MainExcerciseActivity.this.excDurGlobal = length;
                MainExcerciseActivity.this.pauseMainExcersise.setVisibility(0);
                MainExcerciseActivity.this.resumeMainExcersise.setVisibility(8);
                MainExcerciseActivity.this.mainExcTimer(length, MainExcerciseActivity.this.mainExcCounter, MainExcerciseActivity.this.mainExcProgress);
            }

            public void onTick(long j) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("progressbar: ");
                stringBuilder.append(((int) j) / 1000);
                Log.i("readyToGoTimer", stringBuilder.toString());
                long j2 = j - 1000;
                MainExcerciseActivity.this.timerprogress.setProgress(((int) j2) / 1000);
                TextView access$3300 = MainExcerciseActivity.this.count;
                StringBuilder stringBuilder2 = new StringBuilder();
                j2 /= 1000;
                stringBuilder2.append(j2);
                stringBuilder2.append("");
                access$3300.setText(stringBuilder2.toString());
                MainExcerciseActivity.this.s1 = j;
                if (j2 < 4) {
                    if (j2 == 3) {
                        MainExcerciseActivity.this.absWomenApplication.speak("three ");
                    }
                    if (j2 == 2) {
                        MainExcerciseActivity.this.absWomenApplication.speak("two ");
                    }
                    if (j2 == 1) {
                        MainExcerciseActivity.this.absWomenApplication.speak("one ");
                    }
                    if (j2 == 0) {
                        MainExcerciseActivity.this.absWomenApplication.speak("let's start ");
                    }
                }
            }
        }.start();
    }

    void restTimerFun(long j) {
        Object toUpperCase = ((Ex_WorkoutData) this.exWorkoutDataList.get(this.excCouner)).getExcName().replace("_", " ").toUpperCase();
        this.nextExerciseName.setText(toUpperCase.toString());
        TextView textView = this.nextExerciseCycles;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("x");
        stringBuilder.append(((Ex_WorkoutData) this.exWorkoutDataList.get(this.excCouner)).getExcCycles());
        textView.setText(stringBuilder.toString());
        this.nextExerciseAnimation.reset();
        for (int addImageFrame : ((Ex_WorkoutData) this.exWorkoutDataList.get(this.excCouner)).getImageIdList()) {
            this.nextExerciseAnimation.addImageFrame(addImageFrame);
        }
        this.nextExerciseAnimation.startAnimation();
        this.restTimerprogress.setMax((int) (this.REST_TIME_IN_MS / 1000));
        if (j == this.REST_TIME_IN_MS) {
            this.absWomenApplication.speak("Take rest");
            Excercise_AbsWomenApplication absWomenApplication = this.absWomenApplication;
            stringBuilder = new StringBuilder();
            stringBuilder.append("the next exercise is ");
            stringBuilder.append(toUpperCase);
            absWomenApplication.speak(stringBuilder.toString());
        }
        this.restTimer = new CountDownTimer(j, 1000) {
            public void onFinish() {
                MainExcerciseActivity.this.restScreen.setVisibility(8);
                long length = (long) (((((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(MainExcerciseActivity.this.excCouner)).getImageIdList().length > 2 ? ((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(MainExcerciseActivity.this.excCouner)).getImageIdList().length * ((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(MainExcerciseActivity.this.excCouner)).getExcCycles() : ((Ex_WorkoutData) MainExcerciseActivity.this.exWorkoutDataList.get(MainExcerciseActivity.this.excCouner)).getExcCycles()) + 1) * 1000);
                MainExcerciseActivity.this.excDurGlobal = length;
                MainExcerciseActivity.this.pauseMainExcersise.setVisibility(0);
                MainExcerciseActivity.this.resumeMainExcersise.setVisibility(8);
                MainExcerciseActivity.this.mainExcTimer(length, MainExcerciseActivity.this.mainExcCounter, MainExcerciseActivity.this.mainExcProgress);
            }

            @SuppressLint({"SetTextI18n"})
            public void onTick(long j) {
                long j2 = (j - 1000) / 1000;
                MainExcerciseActivity.this.restTimerprogress.setProgress((int) j2);
                TextView access$2200 = MainExcerciseActivity.this.countRestTimer;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(j2);
                stringBuilder.append("");
                access$2200.setText(stringBuilder.toString());
                MainExcerciseActivity.this.s1 = j;
                if (j2 < 4) {
                    if (j2 == 3) {
                        MainExcerciseActivity.this.absWomenApplication.speak("three ");
                    }
                    if (j2 == 2) {
                        MainExcerciseActivity.this.absWomenApplication.speak("two ");
                    }
                    if (j2 == 1) {
                        MainExcerciseActivity.this.absWomenApplication.speak("one ");
                    }
                    if (j2 == 0) {
                       // MainExcerciseActivity.this.absWomenApplication.speak(TtmlNode.START);
                    }
                }
            }
        }.start();


    }

}
