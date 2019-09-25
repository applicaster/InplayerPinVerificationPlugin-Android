package com.applicaster.pinverification.ui.activities;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.applicaster.pinverification.Constants;
import com.applicaster.pinverification.interfaces.PCNetworkResponse;
import com.applicaster.pinverification.networking.PCNetworkRepo;
import com.applicaster.pinverification.ui.adapters.PVPinCodeAdapter;
import com.applicaster.pinverification.utils.Configurations;
import com.applicaster.pinverification.utils.CustomizationHelper;
import com.applicaster.pinverification.utils.DialogHelper;
import com.applicaster.plugin_manager.login.LoginManager;
import com.applicaster.util.OSUtil;

import java.util.HashMap;
import java.util.Map;


public class PVMainActivity extends AppCompatActivity implements View.OnClickListener {


    private LinearLayoutManager layoutManager;
    private PVPinCodeAdapter adapter;

    private ImageView ivBackground;
    private ImageView ivLogo;
    private ImageView ivClose;
    private TextView tvTitle;
    private TextView tvIncorrectPinCode;
    private TextView tvResendPinCode;
    private Button btnProceed;
    private RecyclerView rvPinCode;

    private Map<String, String> configMap;
    private Configurations configurations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(OSUtil.getLayoutResourceIdentifier("pv_activity_main"));

        getConfigurationData();
        viewInit();
        recyclerViewInit();
        gesturesInit();
        setConfigurationData();
    }

    private void viewInit() {
        ivBackground = findViewById(OSUtil.getResourceId("pv_background"));
        ivLogo = findViewById(OSUtil.getResourceId("pv_logo"));
        ivClose = findViewById(OSUtil.getResourceId("pv_btn_close"));
        tvTitle = findViewById(OSUtil.getResourceId("pin_code_main_title"));
        tvIncorrectPinCode = findViewById(OSUtil.getResourceId("pv_incorrect_code"));
        tvResendPinCode = findViewById(OSUtil.getResourceId("pin_code_main_resent_code_TV"));
        btnProceed = findViewById(OSUtil.getResourceId("pin_code_main_continue_BTN"));
        rvPinCode = findViewById(OSUtil.getResourceId("pin_code_main_recyclerView"));
    }

    private void recyclerViewInit() {
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvPinCode.setLayoutManager(layoutManager);
        adapter = new PVPinCodeAdapter(this, rvPinCode, configurations);
        rvPinCode.setAdapter(adapter);
    }

    private void gesturesInit() {
        tvResendPinCode.setOnClickListener(this);
        btnProceed.setOnClickListener(this);
        ivClose.setOnClickListener(this);
    }

    private void getConfigurationData() {
        Intent intent = getIntent();
        if (intent != null) {
            configMap = (HashMap<String, String>) intent.getSerializableExtra(Constants.EXTRA_PIN_VERIFICATION_PROPS);
            configurations = new Configurations(configMap);
        }
    }

    private void setConfigurationData() {
        ivLogo.setImageResource(OSUtil.getDrawableResourceIdentifier(configurations.getLogoImage()));

        ivClose.setImageResource(OSUtil.getDrawableResourceIdentifier(configurations.getCloseButtonImage()));

        if (!TextUtils.isEmpty(configurations.getScreenBackgroundImage())) {
            ivBackground.setImageResource(OSUtil.getDrawableResourceIdentifier(configurations.getScreenBackgroundImage()));
        } else {
            ivBackground.setImageResource(0);
            ivBackground.setBackgroundColor(configurations.getScreenBackgroundColor());
        }

        CustomizationHelper.setTextViewProps(tvTitle,
                configurations.getTitleText(),
                configurations.getTitleFont(),
                configurations.getTitleTextSize(),
                configurations.getTitleColor());

        CustomizationHelper.setTextViewProps(tvIncorrectPinCode,
                configurations.getIncorrectPinText(),
                configurations.getIncorrectPinFont(),
                configurations.getIncorrectPinTextSize(),
                configurations.getIncorrectPinTextColor());

        tvResendPinCode.setPaintFlags(tvIncorrectPinCode.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        CustomizationHelper.setTextViewProps(tvResendPinCode,
                configurations.getResendPinText(),
                configurations.getResendPinFont(),
                configurations.getResendPinTextSize(),
                configurations.getResendPinTextColor());
        tvResendPinCode.setTypeface(tvResendPinCode.getTypeface(), Typeface.BOLD);


        CustomizationHelper.setButtonPropsWithBackground(btnProceed,
                configurations.getProceedButtonText(),
                configurations.getProceedButtonFont(),
                configurations.getProceedButtonTextSize(),
                configurations.getProceedButtonTextColor(),
                configurations.getProceedButtonBackgroundColor(),
                configurations.getProceedButtonCornersRadius());
        btnProceed.setTypeface(btnProceed.getTypeface(), Typeface.BOLD);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == OSUtil.getResourceId("pin_code_main_resent_code_TV")) {    // Resend passcode
            tvIncorrectPinCode.setVisibility(View.INVISIBLE);
            DialogHelper.getInstance().displayLoaderProgressDialog(this).showLoader();
            getNewPinCode();

        } else if (v.getId() == OSUtil.getResourceId("pin_code_main_continue_BTN")) {   // Proceed button
            tvIncorrectPinCode.setVisibility(View.INVISIBLE);
            if (adapter.isPinCodeReady()) {
                DialogHelper.getInstance().displayLoaderProgressDialog(this).showLoader();
                sendPinCode(adapter.getPinCode());
            }
        } else if (v.getId() == OSUtil.getResourceId("pv_btn_close")) {
            finishActivity();
        }
    }

    private void sendPinCode(String pinCode) {
        String token = LoginManager.getLoginPlugin().getToken();

        PCNetworkRepo.getInstance().setPinCode(pinCode, configurations.getPublisherId(), token, new PCNetworkResponse() {
            @Override
            public void onSuccess(Object data) {
                DialogHelper.getInstance().dismissLoader();
                sendOkResult();
            }

            @Override
            public void onError(Object data) {
                DialogHelper.getInstance().dismissLoader();
                deletePinCode();
                tvIncorrectPinCode.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Object data) {
                DialogHelper.getInstance().dismissLoader();
                tvIncorrectPinCode.setVisibility(View.VISIBLE);

                Toast.makeText(PVMainActivity.this, (String) data, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getNewPinCode() {
        String token = LoginManager.getLoginPlugin().getToken();

        PCNetworkRepo.getInstance().getNewPinCode(token, configurations.getPublisherId(), new PCNetworkResponse() {
            @Override
            public void onSuccess(Object data) {
                DialogHelper.getInstance().displayNotificationDialog(PVMainActivity.this, (String) data, configurations).showNotice();
            }

            @Override
            public void onError(Object data) {
                DialogHelper.getInstance().displayNotificationDialog(PVMainActivity.this, (String) data, configurations).showNotice();
            }

            @Override
            public void onFailure(Object data) {
                DialogHelper.getInstance().displayNotificationDialog(PVMainActivity.this, (String) data, configurations).showNotice();
            }
        });
    }

    private void deletePinCode() {
        rvPinCode.setAdapter(null);
        rvPinCode.setLayoutManager(null);
        recyclerViewInit();
    }

    private void sendOkResult() {
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finishActivity();
    }

    private void finishActivity() {
        finish();
    }
}
