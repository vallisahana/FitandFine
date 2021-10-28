package com.example.fitandfine.Ex_activities;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback;

final class MainExcerciseActivity_exitConfirmDialog_No implements SingleButtonCallback {
    static final SingleButtonCallback $instance = new MainExcerciseActivity_exitConfirmDialog_No();

    private MainExcerciseActivity_exitConfirmDialog_No() {
    }

    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
        materialDialog.dismiss();
    }
}
