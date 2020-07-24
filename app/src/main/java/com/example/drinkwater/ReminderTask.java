package com.example.drinkwater;

import android.content.Context;

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
