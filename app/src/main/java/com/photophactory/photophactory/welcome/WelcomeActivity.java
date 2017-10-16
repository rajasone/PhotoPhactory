package com.photophactory.photophactory.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.photophactory.photophactory.BuildConfig;
import com.photophactory.photophactory.R;
import com.photophactory.photophactory.home.HomeActivity;

public class WelcomeActivity extends AppCompatActivity implements WelcomeContract.ActivityView {

    private static final String TAG = WelcomeActivity.class.getSimpleName();
    private WelcomeContract.Presenter presenter;
    private WelcomeFragment welcomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_main);

        welcomeFragment = (WelcomeFragment) getSupportFragmentManager().findFragmentById(R.id.welcome_fragment_container);
        if (welcomeFragment == null) {
            Log.d(TAG, "onCreate: Adding the fragment");
            welcomeFragment = WelcomeFragment.getInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.welcome_fragment_container, welcomeFragment)
                    .commit();
        }
        presenter = new WelcomePresenter(getSharedPreferences(BuildConfig.WELCOME_SHARED_PREFS, MODE_PRIVATE), this, welcomeFragment);
        if (presenter.isWelcomeIsComplete()) {
            startHomeActivity();
        }
        welcomeFragment.setPresenter(presenter);
        Log.d(TAG, "onCreate: end");
    }

    @Override
    public void startHomeActivity() {
        Intent homeActivity = new Intent(this, HomeActivity.class);
        homeActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeActivity);
    }
}
