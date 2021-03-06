package com.world.protester;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class ProtesterApplication extends Application {

    public static final String BASE_URL = "http://192.168.1.4:8000/";
    public static final String DATA_FLAG_SPLASH = "from_splash";

    public static final String PROTESTER = "PROTESTER";

    public ProtesterApplication() {
        super();

    }

    @Override public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
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
