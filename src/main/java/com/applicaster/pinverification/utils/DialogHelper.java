package com.applicaster.pinverification.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.applicaster.pinverification.R;


public class DialogHelper {
    private final String TAG = DialogHelper.class.getSimpleName();

    private AlertDialog loaderAlertDialog;
    private AlertDialog noticeAlertDialog;
    private static DialogHelper dialogHelper;

    private DialogHelper() {
    }

    public static DialogHelper getInstance() {
        if (dialogHelper == null) {
            dialogHelper = new DialogHelper();
        }

        return dialogHelper;
    }

    public DialogHelper displayNotificationDialog(Activity activity, String message, Configurations configurations) {
        dismissLoader();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.pv_dialog_notice, null);

        TextView tvMessage = dialogView.findViewById(R.id.pv_dialog_message);
        Button btnOK = dialogView.findViewById(R.id.pv_dialog_btn_ok);
        View background = dialogView.findViewById(R.id.pv_dialog_bg);
        background.setBackgroundColor(configurations.getDialogBackgroundColor());

        CustomizationHelper.setTextViewProps(tvMessage, message,
                configurations.getDialogMessageFont(),
                configurations.getDialogMessageTextSize(),
                configurations.getDialogMessageTextColor());

        CustomizationHelper.setButtonPropsWithBackground(btnOK, configurations.getDialogButtonText(),
                configurations.getDialogButtonFont(),
                configurations.getDialogButtonTextSize(),
                configurations.getDialogButtonTextColor(),
                configurations.getDialogButtonBgColor(),
                configurations.getDialogButtonBgRadius());

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noticeAlertDialog.dismiss();
            }
        });

        builder.setView(dialogView);
        builder.setCancelable(false);

        noticeAlertDialog = builder.create();
        noticeAlertDialog.getWindow().getDecorView().setBackground(new ColorDrawable(Color.TRANSPARENT));


        return this;
    }

    public DialogHelper displayLoaderProgressDialog(Context context) {
        loaderAlertDialog = ProgressDialog.show(context, null, null, true, false);
        loaderAlertDialog.setContentView(R.layout.pv_dialog_loader);
        loaderAlertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        return this;
    }

    public boolean isLoaderShown() {
        if (loaderAlertDialog != null) {
            return loaderAlertDialog.isShowing();
        } else {
            return false;
        }
    }

    public boolean isNoticeShown() {
        if (noticeAlertDialog != null) {
            return noticeAlertDialog.isShowing();
        } else {
            return false;
        }
    }

    public void showLoader() {
        if (!loaderAlertDialog.isShowing()) {
            loaderAlertDialog.show();
        }
    }

    public void showNotice() {
        if (!noticeAlertDialog.isShowing()) {
            noticeAlertDialog.show();
        }
    }

    public void dismissLoader() {
        if (isLoaderShown()) {
            loaderAlertDialog.dismiss();
        }
    }

    public void dismissNotice() {
        if (isLoaderShown()) {
            loaderAlertDialog.dismiss();
        }
    }

}
