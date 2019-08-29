package com.applicaster.pinverification.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.applicaster.pinverification.R;
import com.applicaster.pinverification.interfaces.PCNetworkResponse;
import com.applicaster.pinverification.networking.PCNetworkRepo;
import com.applicaster.pinverification.ui.adapters.PVPinCodeAdapter;
import com.applicaster.plugin_manager.login.LoginManager;
import com.applicaster.util.OSUtil;

import java.io.Serializable;
import java.util.Map;


public class PVMainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView pin_code_main_title, pin_code_main_continue_BTN, pin_code_mailn_resent_code_TV;
    private RecyclerView pin_code_main_recyclerView;
    private LinearLayoutManager layoutManager;
    private PVPinCodeAdapter adapter;
    private int numberOnPinItems = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pv_activity_main);

        viewInit();
        recyclerViewInit();
        gesturesInit();
        setConfigurationData();

    }


    private void viewInit() {
        pin_code_main_title = findViewById(R.id.pin_code_main_title);
        pin_code_main_continue_BTN = findViewById(R.id.pin_code_main_continue_BTN);
        pin_code_mailn_resent_code_TV = findViewById(R.id.pin_code_main_resent_code_TV);
        pin_code_main_recyclerView = findViewById(R.id.pin_code_main_recyclerView);
    }

    private void recyclerViewInit() {
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                layoutManager.getOrientation());
        this.pin_code_main_recyclerView.setLayoutManager(layoutManager);
        this.pin_code_main_recyclerView.addItemDecoration(dividerItemDecoration);
        adapter = new PVPinCodeAdapter(numberOnPinItems, this, pin_code_main_recyclerView);
        this.pin_code_main_recyclerView.setAdapter(adapter);

    }

    private void gesturesInit() {
        pin_code_mailn_resent_code_TV.setOnClickListener(this);
        pin_code_main_continue_BTN.setOnClickListener(this);
    }

    private void setConfigurationData() {

    }

    public static void launchPVMainActivity(Context context) {
        Intent intent = new Intent(context, PVMainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(0, 0);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == OSUtil.getResourceId("pin_code_main_resent_code_TV")) {
            getNewPinCode();

        } else if (v.getId() == OSUtil.getResourceId("pin_code_main_continue_BTN")) {
            sendPinCode(PVPinCodeAdapter.getPinCode());
        }
    }


    private void sendPinCode(String pinCode) {
        String token = LoginManager.getLoginPlugin().getToken();
        PCNetworkRepo.getInstance().setPinCode(pinCode, token, new PCNetworkResponse() {
            @Override
            public void onSuccess(Object data) {
                sendOkResoult();
            }

            @Override
            public void onError(Object data) {
                Toast.makeText(PVMainActivity.this, (String) data, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Object data) {
                Toast.makeText(PVMainActivity.this, (String) data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getNewPinCode() {
        String brandingID = "1";
        String token = LoginManager.getLoginPlugin().getToken();
        PCNetworkRepo.getInstance().getNewPinCode(brandingID, token, new PCNetworkResponse() {
            @Override
            public void onSuccess(Object data) {

            }

            @Override
            public void onError(Object data) {
                Toast.makeText(PVMainActivity.this, (String) data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Object data) {
                Toast.makeText(PVMainActivity.this, (String) data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendOkResoult() {
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }


}
