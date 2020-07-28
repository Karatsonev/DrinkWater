package com.example.drinkwater;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;

public class MainActivityPresenter implements MainActivityContract.Presenter,
        SharedPreferences.OnSharedPreferenceChangeListener {

    private MainActivityContract.View mView;
    private Context mContext;

    public MainActivityPresenter(Context context, MainActivityContract.View view) {
        mView = view;
        mContext = context;
    }

    @Override
    public void onViewCreated() {
        mView.initViews();
        mView.initCounter();
    }

    @Override
    public void incrementCounter() {
        Intent incrementCounterIntent = new Intent(mContext, ReminderIntentService.class);
        incrementCounterIntent.setAction(ReminderTask.ACTION_INCREMENT_WATER_COUNT);
        mContext.startService(incrementCounterIntent);
    }

    @Override
    public int getCounterNumber() {
        return PreferencesUtils.getWaterCount(mContext);
    }

    @Override
    public void registerPrefsListener() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void unregisterPrefsListener() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(PreferencesUtils.KEY_WATER_COUNT)) {
            mView.updateCounter(PreferencesUtils.getWaterCount(mContext));
        }
    }
}
