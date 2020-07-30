package com.example.drinkwater.reminder;

import android.content.Context;

import com.example.drinkwater.data.PreferencesUtils;

public class ReminderTask {

    public static final String ACTION_INCREMENT_WATER_COUNT = "increment-water-count";

    public static void executeTask(Context context, String action) {
        if (ACTION_INCREMENT_WATER_COUNT.equals(action)) {
           incrementWaterCount(context);
        }
    }

    private static void incrementWaterCount(Context context) {
        PreferencesUtils.incrementWaterCount(context);
    }

}
