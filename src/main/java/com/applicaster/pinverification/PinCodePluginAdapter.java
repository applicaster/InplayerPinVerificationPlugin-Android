package com.applicaster.pinverification;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.applicaster.pinverification.ui.activities.PVMainActivity;
import com.applicaster.plugin_manager.Plugin;
import com.applicaster.plugin_manager.screen.PluginScreen;
import com.applicaster.pluginpresenter.PluginPresenter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class PinCodePluginAdapter implements PluginScreen, PluginPresenter {

    private static final String TAG = PinCodePluginAdapter.class.getSimpleName();

    private Map<String, String> configMap;

    @Override
    public void present(Context context, HashMap<String, Object> screenMap, Serializable dataSource, boolean isActivity) {
        Log.d(TAG, "present: ");
    }

    @Override
    public Fragment generateFragment(HashMap<String, Object> screenMap, Serializable dataSource) {
        Log.d(TAG, "generateFragment: null");
        return null;
    }


    @Override
    public void setPluginModel(Plugin plugin) {
        configMap = plugin.configuration;
        addItemsToMap();
    }

    @Override
    public void presentPlugin(Activity activity, Object o) {

        Intent intent = new Intent(activity, PVMainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.EXTRA_PIN_VERIFICATION_PROPS, (Serializable) configMap);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, PLUGIN_PRESENTER_REQUEST_CODE);
    }

    private void addItemsToMap() {
        configMap.put("pin_verification_logo_image", "cleeng_login_logo");
        configMap.put("pin_verification_close_button_image", "cleeng_login_close_button");
        configMap.put("pin_verification_screen_bg_color", "#000000");
        configMap.put("pin_verification_screen_bg_image", "");
        configMap.put("pin_verification_title_text", "Please enter the access code you received by email");
        configMap.put("pin_verification_title_font", "Sport1-Regular");
        configMap.put("pin_verification_title_size", "18");
        configMap.put("pin_verification_title_color", "#FFFFFF");
        configMap.put("pin_verification_pin_code_length", "4");
        configMap.put("pin_verification_pin_code_font", "Sport1-Regular");
        configMap.put("pin_verification_pin_code_size", "24");
        configMap.put("pin_verification_pin_code_color", "#000000");
        configMap.put("pin_verification_pin_code_bg_color", "#FFFFFF");
        configMap.put("pin_verification_incorrect_pin_text", "The code you entered is wrong! Please try again");
        configMap.put("pin_verification_incorrect_pin_font", "Sport1-Regular");
        configMap.put("pin_verification_incorrect_pin_size", "18");
        configMap.put("pin_verification_incorrect_pin_color", "#EAA843");
        configMap.put("pin_verification_resend_pin_text", "Resend passcode");
        configMap.put("pin_verification_resend_pin_font", "Sport1-Regular");
        configMap.put("pin_verification_resend_pin_size", "18");
        configMap.put("pin_verification_resend_pin_color", "#FFFFFF");
        configMap.put("pin_verification_proceed_button_text", "CONTINUE");
        configMap.put("pin_verification_proceed_button_font", "Sport1-Regular");
        configMap.put("pin_verification_proceed_button_size", "18");
        configMap.put("pin_verification_proceed_button_color", "#FFFFFF");
        configMap.put("pin_verification_proceed_button_bg_color", "#EDAA44");
        configMap.put("pin_verification_proceed_button_corners_radius", "10");
        configMap.put("pin_verification_dialog_bg_color", "#1F1F1F");
        configMap.put("pin_verification_dialog_message_font", "Sport1-Regular");
        configMap.put("pin_verification_dialog_message_size", "18");
        configMap.put("pin_verification_dialog_message_color", "#FFFFFF");
        configMap.put("pin_verification_dialog_confirm_button_text", "OK");
        configMap.put("pin_verification_dialog_confirm_button_font", "Sport1-Regular");
        configMap.put("pin_verification_dialog_confirm_button_size", "18");
        configMap.put("pin_verification_dialog_confirm_button_color", "#FFFFFF");
        configMap.put("pin_verification_dialog_confirm_button_bg_color", "#EDAA44");
        configMap.put("pin_verification_dialog_confirm_button_corners_radius", "5");
    }
}
