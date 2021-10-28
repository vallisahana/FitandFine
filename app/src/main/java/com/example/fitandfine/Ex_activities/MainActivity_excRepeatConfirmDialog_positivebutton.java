package com.example.fitandfine.Ex_activities;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback;

final class MainActivity_excRepeatConfirmDialog_positivebutton implements SingleButtonCallback {
    private final MainActivity arg$1;
    private final int arg$2;

    MainActivity_excRepeatConfirmDialog_positivebutton(MainActivity mainActivity, int i) {
        this.arg$1 = mainActivity;
        this.arg$2 = i;
    }

    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
        this.arg$1.lambda$excRepeatConfirmDialog$0$MainActivity(this.arg$2, materialDialog, dialogAction);
    }
}
