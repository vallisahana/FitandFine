package com.example.fitandfine.Ex_activities;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback;

final class MainActivity_excRepeatConfirmDialog_nagativebutton implements SingleButtonCallback {
    static final SingleButtonCallback $instance = new MainActivity_excRepeatConfirmDialog_nagativebutton();

    private MainActivity_excRepeatConfirmDialog_nagativebutton() {
    }

    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
        materialDialog.dismiss();
    }
}
