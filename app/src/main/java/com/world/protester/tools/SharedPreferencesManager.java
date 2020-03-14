package com.world.protester.tools;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private static final String APP_SETTINGS = "PROTESTOR_SETTINGS";

    private static final String CURRENT_CITY_VALUE = "CURRENT_CITY";
    private static final String FIRST_LAUNCH = "FIRST_LAUNCH";

    private SharedPreferencesManager() {}

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
    }

    public static String getCurrentCity(Context context) {
        return getSharedPreferences(context).getString(CURRENT_CITY_VALUE , null);
    }

    public static void setCurrentCity(Context context, String newValue) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(CURRENT_CITY_VALUE , newValue);
        editor.apply();
    }

    public static boolean isFirstLaunch(Context context) {
        return getSharedPreferences(context).getBoolean(FIRST_LAUNCH , true);
    }

    public static void disableFirstLaunch(Context context) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(FIRST_LAUNCH , false);
        editor.apply();
    }

}
