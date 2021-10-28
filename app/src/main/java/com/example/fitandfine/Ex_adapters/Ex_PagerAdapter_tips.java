package com.example.fitandfine.Ex_adapters;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.fitandfine.Ex_fragments.Ex_Tipsfragment;

public class Ex_PagerAdapter_tips extends FragmentStatePagerAdapter {
    public Ex_PagerAdapter_tips(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public int getCount() {
        return 10;
    }

    public Fragment getItem(int i) {
        Fragment tipsfragment = new Ex_Tipsfragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", i);
        tipsfragment.setArguments(bundle);
        return tipsfragment;
    }
}
