package com.photophactory.photophactory.welcome;

import android.content.SharedPreferences;

import com.photophactory.photophactory.BuildConfig;

/**
 * Created by rajaSaboor on 10/1/2017.
 */

public class WelcomePresenter implements WelcomeContract.Presenter {
    private static final String TAG = WelcomePresenter.class.getSimpleName();
    private SharedPreferences sharedPreferences;
    private WelcomeContract.ActivityView activityView;
    private WelcomeContract.FragmentView fragmentView;

    public WelcomePresenter(SharedPreferences sharedPreferences, WelcomeContract.ActivityView activityView, WelcomeContract.FragmentView fragmentView) {
        this.sharedPreferences = sharedPreferences;
        this.activityView = activityView;
        this.fragmentView = fragmentView;
    }

    @Override
    public void setWelcomeCompleteSharedPrefs() {
        sharedPreferences.edit().putBoolean(BuildConfig.WELCOME_SHARED_PREFS_STATUS_KEY, true).apply();
    }

    @Override
    public void deleteSharedPrefs() {
        sharedPreferences.edit().clear().apply();
    }

    @Override
    public boolean isWelcomeIsComplete() {
        return sharedPreferences.getBoolean(BuildConfig.WELCOME_SHARED_PREFS_STATUS_KEY, false);
    }

    @Override
    public void skipTutorialActivity() {
        setWelcomeCompleteSharedPrefs();
        activityView.startHomeActivity();
    }

    @Override
    public void nextTutorialButton(int currentIndex) {
        if (currentIndex == BuildConfig.TOTAL_TABS) {
            setWelcomeCompleteSharedPrefs();
            activityView.startHomeActivity();
        } else {
            fragmentView.nextSlide(currentIndex);
        }
    }
}
