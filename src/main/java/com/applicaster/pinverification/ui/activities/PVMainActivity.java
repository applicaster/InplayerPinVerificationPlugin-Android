package com.applicaster.pinverification.ui.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.applicaster.pinverification.R;
import com.applicaster.pinverification.interfaces.OnPincodeReady;
import com.applicaster.pinverification.interfaces.PCNetworkResponse;
import com.applicaster.pinverification.networking.PCNetworkRepo;
import com.applicaster.pinverification.ui.adapters.PVPinCodeAdapter;
import com.applicaster.pinverification.utils.DialogHelper;
import com.applicaster.plugin_manager.login.LoginManager;
import com.applicaster.util.OSUtil;

import static com.applicaster.pluginpresenter.PluginPresenter.PLUGIN_PRESENTER_REQUEST_CODE;


public class PVMainActivity extends AppCompatActivity implements View.OnClickListener, OnPincodeReady {


    private TextView pin_code_main_title, pin_code_main_continue_BTN, pin_code_mailn_resent_code_TV;
    private RecyclerView pin_code_main_recyclerView;
    private LinearLayoutManager layoutManager;
    private PVPinCodeAdapter adapter;
    private int numberOnPinItems = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(OSUtil.getLayoutResourceIdentifier("pv_activity_main"));

        viewInit();
        recyclerViewInit();
        gesturesInit();
        setConfigurationData();

    }


    private void viewInit() {
        pin_code_main_title = findViewById(OSUtil.getResourceId("pin_code_main_title"));
        pin_code_main_continue_BTN = findViewById(OSUtil.getResourceId("pin_code_main_continue_BTN"));
        pin_code_mailn_resent_code_TV = findViewById(OSUtil.getResourceId("pin_code_main_resent_code_TV"));
        pin_code_main_recyclerView = findViewById(OSUtil.getResourceId("pin_code_main_recyclerView"));
    }

    private void recyclerViewInit() {
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                layoutManager.getOrientation());
        this.pin_code_main_recyclerView.setLayoutManager(layoutManager);
        this.pin_code_main_recyclerView.addItemDecoration(dividerItemDecoration);
        adapter = new PVPinCodeAdapter(numberOnPinItems, this, pin_code_main_recyclerView, this);
        this.pin_code_main_recyclerView.setAdapter(adapter);

    }

    private void gesturesInit() {
        pin_code_mailn_resent_code_TV.setOnClickListener(this);
        pin_code_main_continue_BTN.setOnClickListener(this);
    }

    private void setConfigurationData() {

    }

    @Override
    public void onClick(View v) {
        DialogHelper.getInstance().displayLoaderProgressDialog(this).show();
        if (v.getId() == OSUtil.getResourceId("pin_code_main_resent_code_TV")) {

            getNewPinCode();

        } else if (v.getId() == OSUtil.getResourceId("pin_code_main_continue_BTN")) {
            sendPinCode(PVPinCodeAdapter.getPinCode());

        }
    }


    private void sendPinCode(String pinCode) {
        String token = LoginManager.getLoginPlugin().getToken();
        PCNetworkRepo.getInstance().setPinCode("YWD2C", token, new PCNetworkResponse() {
            @Override
            public void onSuccess(Object data) {
                sendOkResoult();
                DialogHelper.getInstance().dismiss();
            }

            @Override
            public void onError(Object data) {
                Toast.makeText(PVMainActivity.this, (String) data, Toast.LENGTH_SHORT).show();
                DialogHelper.getInstance().dismiss();
            }

            @Override
            public void onFailure(Object data) {
                Toast.makeText(PVMainActivity.this, (String) data, Toast.LENGTH_SHORT).show();
                DialogHelper.getInstance().dismiss();
            }
        });
    }

    private void getNewPinCode() {
        String brandingID = "1";
        String token = LoginManager.getLoginPlugin().getToken();
        PCNetworkRepo.getInstance().getNewPinCode(brandingID, token, new PCNetworkResponse() {
            @Override
            public void onSuccess(Object data) {
                DialogHelper.getInstance().dismiss();
            }

            @Override
            public void onError(Object data) {
                DialogHelper.getInstance().dismiss();
                Toast.makeText(PVMainActivity.this, (String) data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Object data) {
                DialogHelper.getInstance().dismiss();
                Toast.makeText(PVMainActivity.this, (String) data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendOkResoult() {
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }


    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, PVMainActivity.class);
        activity.startActivityForResult(intent, PLUGIN_PRESENTER_REQUEST_CODE);

    }

    @Override
    public void pinCodeReady(boolean isReady) {
        if (isReady) {
            pin_code_main_continue_BTN.setEnabled(true);
            pin_code_main_continue_BTN.setClickable(true);
        } else {
            pin_code_main_continue_BTN.setEnabled(false);
            pin_code_main_continue_BTN.setClickable(false);
        }

    }

}
