package com.example.fitandfine.Ex_activities;

import android.view.View;

import com.example.fitandfine.utils.utils_RecyclerItemClickListener;


final  class Ex_DayActivity_buttonOn_Click implements utils_RecyclerItemClickListener.onItemClickListener {
    private final Ex_DayActivity arg$1;

    Ex_DayActivity_buttonOn_Click(Ex_DayActivity exDayActivity) {
        this.arg$1 = exDayActivity;
    }

    public void OnItem(View view, int i) {
        this.arg$1.lambda$onCreate$0$DayActivity(view, i);
    }
}
