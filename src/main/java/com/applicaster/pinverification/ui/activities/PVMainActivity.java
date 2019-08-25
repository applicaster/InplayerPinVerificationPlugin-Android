package com.applicaster.pinverification.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.applicaster.pinverification.R;
import com.applicaster.pinverification.ui.adapters.PVPinCodeAdapter;
import com.applicaster.util.OSUtil;

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
        this.pin_code_main_recyclerView.setLayoutManager(layoutManager);
        adapter = new PVPinCodeAdapter(numberOnPinItems, this);
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
        if (v.getId() == OSUtil.getResourceId("pin_code_main_resent_code_TV")) {
            //TODO... resent code

        } else if (v.getId() == OSUtil.getResourceId("pin_code_main_continue_BTN")) {
            //TODO continue..
        }
    }
}
