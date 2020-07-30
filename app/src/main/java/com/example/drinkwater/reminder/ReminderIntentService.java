package com.example.drinkwater.reminder;

import android.app.IntentService;
import android.content.Intent;
import androidx.annotation.Nullable;
import java.util.Objects;

public class ReminderIntentService extends IntentService {

    public ReminderIntentService() {
        super("ReminderIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = Objects.requireNonNull(intent).getAction();
        ReminderTask.executeTask(this,action);
    }
}
