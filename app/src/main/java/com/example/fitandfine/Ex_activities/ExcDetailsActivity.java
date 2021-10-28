package com.example.fitandfine.Ex_activities;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.example.fitandfine.R;

import kr.pe.burt.android.lib.faimageview.FAImageView;

public class ExcDetailsActivity extends AppCompatActivity {

    static LayoutParams layoutParams;
   // private AdChoicesView adChoicesView;
    private LinearLayout adView;
    Context context;
    int excNameDescResId;
    LayoutInflater inflater;
   // private NativeAd nativeAd;
    private LinearLayout nativeAdContainer;
    int screenheight;
    int screenwidth;
    int testHeight = 0;
    TextView textViewExcDescription;
    private AdView mAdView;


    public static int dpToPx() {
        return (int) (Resources.getSystem().getDisplayMetrics().density * 50.0f);
    }

    private void getScreenHeightWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.screenheight = displayMetrics.heightPixels;
        this.screenwidth = displayMetrics.widthPixels;
    }



    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView((int) R.layout.exc_details_layout);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        this.context = this;
      //  mAdView = findViewById(R.id.adView);
       /* AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/
        Bundle extras = getIntent().getExtras();
        int[] intArray = extras.getIntArray("framesIdArray");
        String string = extras.getString("excName");
        extras.getInt("excCycle");
        this.excNameDescResId = extras.getInt("excNameDescResId");
        CharSequence toUpperCase = string.replace("_", " ").toUpperCase();
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.getClass();
        supportActionBar.setTitle(toUpperCase);
        //getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        this.textViewExcDescription = (TextView) findViewById(R.id.description_exDetail);
        FAImageView fAImageView = (FAImageView) findViewById(R.id.animation_exDetail);
        fAImageView.setInterval(1000);
        fAImageView.setLoop(true);
        fAImageView.reset();
        for (int addImageFrame : intArray) {
            fAImageView.addImageFrame(addImageFrame);
        }
        fAImageView.startAnimation();
        this.textViewExcDescription.setText(this.excNameDescResId);
        getScreenHeightWidth();
      //  displayNativeAd();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

}
