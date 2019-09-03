package com.applicaster.pinverification.utils;


import android.app.AlertDialog;
import android.app.ProgressDialog;

import android.content.Context;


import android.graphics.drawable.ColorDrawable;

import com.applicaster.pinverification.R;


public class DialogHelper {
    private final String TAG = DialogHelper.class.getSimpleName();


    private AlertDialog dialog;

    private static DialogHelper dialogHelper;


    private DialogHelper() {

    }

    public static DialogHelper getInstance() {


        if (dialogHelper == null) {
            dialogHelper = new DialogHelper();
        }

        return dialogHelper;
    }


    public DialogHelper displayLoaderProgressDialog(Context context, String message) {


        dialog = new ProgressDialog(context);
        //TODO remove the comment below
        dialog.setCancelable(false);
        // dialog.setMessage(message);

        return this;
    }

    public DialogHelper displayLoaderProgressDialog(Context context) {


        dialog = ProgressDialog.show(context, null, null, true, false);
        dialog.setContentView(R.layout.pv_no_bg_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        return this;

    }


    public boolean isDialogShown() {

        if (dialog != null) {
            return dialog.isShowing();
        } else return false;
    }

    public void show() {
        if (!dialog.isShowing()) {
            dialog.show();
        }


    }

    public void dismiss() {
        if (isDialogShown()) {
            dialog.dismiss();
        }
    }


}
