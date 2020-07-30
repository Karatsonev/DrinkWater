package com.example.drinkwater.data;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;

import javax.inject.Inject;

public class PreferencesUtils  {

    @Inject
    public PreferencesUtils() { }

    public static final String KEY_WATER_COUNT = "water-count";
    private static final int DEFAULT_COUNT = 0;

    synchronized private static void setWaterCount(Context context, int count) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_WATER_COUNT, count);
        editor.apply();
    }

    public static int getWaterCount(Context context) {
       SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt(KEY_WATER_COUNT,DEFAULT_COUNT);
    }

    synchronized public static void incrementWaterCount(Context context) {
        int count =  getWaterCount(context);
        setWaterCount(context, ++count);
    }

    public String testUtils() {
        return "It Works!";
    }

}
