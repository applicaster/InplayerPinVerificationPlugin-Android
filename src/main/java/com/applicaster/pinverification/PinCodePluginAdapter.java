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
    }

    @Override
    public void presentPlugin(Activity activity, Object o) {

        Intent intent = new Intent(activity, PVMainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.EXTRA_PIN_VERIFICATION_PROPS, (Serializable) configMap);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, PLUGIN_PRESENTER_REQUEST_CODE);
    }
}
