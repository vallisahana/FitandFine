package com.example.fitandfine.Ex_activities;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback;

final  class MainExcerciseActivity_exitConfirmDialog_Yes implements SingleButtonCallback {
    private final MainExcerciseActivity arg$1;

    MainExcerciseActivity_exitConfirmDialog_Yes(MainExcerciseActivity mainExcerciseActivity) {
        this.arg$1 = mainExcerciseActivity;
    }

    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
        this.arg$1.lambda$exitConfirmDialog$7$MainExcerciseActivity(materialDialog, dialogAction);
    }
}
