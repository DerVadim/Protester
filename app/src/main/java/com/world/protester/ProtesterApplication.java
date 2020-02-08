package com.world.protester;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class ProtesterApplication extends Application {

    public ProtesterApplication() {
        super();
    }

    public static boolean getConnectionStatus(Context context) {
        ConnectivityManager mConnectivityManager;
        NetworkInfo mNetworkInfoMobile, mNetworkInfoWifi;

        mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        mNetworkInfoMobile = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        mNetworkInfoWifi = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        try {
            if (mNetworkInfoMobile.isConnected()) {

                return true;
            }
        } catch (Exception exception) {
            Log.d(ProtesterApplication.class.getName(), exception.getMessage());
        }

        if (mNetworkInfoWifi.isConnected()) {
            return true;
        } else {
            return false;
        }

    }


}
