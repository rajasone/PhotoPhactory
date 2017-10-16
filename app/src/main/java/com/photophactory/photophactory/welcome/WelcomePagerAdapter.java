package com.photophactory.photophactory.welcome;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.photophactory.photophactory.BuildConfig;


/**
 * Created by rajaSaboor on 9/30/2017.
 */

public class WelcomePagerAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = WelcomePagerAdapter.class.getSimpleName();

    public WelcomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return setFragmentBundle(position);
    }

    private Fragment setFragmentBundle(int position) {
        Log.d(TAG, "setFragmentBundle: start position ---> " + position);
        return WelcomeTabContentFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return BuildConfig.TOTAL_TABS;
    }
}
