package com.applicaster.pinverification;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;


import com.applicaster.pinverification.ui.fragments.PVPMainFragment;
import com.applicaster.plugin_manager.Plugin;
import com.applicaster.plugin_manager.screen.PluginScreen;
import com.applicaster.pluginpresenter.PluginPresenter;

import java.io.Serializable;
import java.util.HashMap;


public class PinCodePluginAdapter implements PluginScreen, PluginPresenter {


    @Override
    public void present(Context context, HashMap<String, Object> screenMap, Serializable dataSource, boolean isActivity) {



    }

    @Override
    public Fragment generateFragment(HashMap<String, Object> screenMap, Serializable dataSource) {
        return PVPMainFragment.newInstance(screenMap);
    }


    @Override
    public void setPluginModel(Plugin plugin) {
        Log.d("IgorTest", "setPluginModel()");
    }

    @Override
    public void presentPlugin(Activity activity, Object o) {
        Log.d("IgorTest", "presentPlugin()");
    }
}
