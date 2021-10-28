package com.example.fitandfine.Ex_activities;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.example.fitandfine.R;
import com.example.fitandfine.Ex_adapters.Ex_PagerAdapter_tips;
import com.example.fitandfine.utils.utils_DepthPageTransformer;


public class Ex_TipsActivity extends AppCompatActivity {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    ViewPager viewPager;
    private AdView mAdView;

    public void close(View view) {
        finish();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView((int) R.layout.exec_tips_layout);
        getSupportActionBar().hide();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

       // mAdView = findViewById(R.id.adView);

        try {
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }catch (Exception e){
            e.printStackTrace();
        }

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        this.viewPager = (ViewPager) findViewById(R.id.viewPager);
        this.viewPager.setAdapter(new Ex_PagerAdapter_tips(supportFragmentManager));
        this.viewPager.setClipToPadding(false);
        this.viewPager.setPadding(80, 0, 120, 120);
        this.viewPager.setPageTransformer(true, new utils_DepthPageTransformer());



    }
}
