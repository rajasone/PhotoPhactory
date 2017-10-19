package com.photophactory.photophactory.home;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by rajaSaboor on 10/7/2017.
 */

public class HomePresenter implements HomeContract.Presenter {
    private static final String TAG = HomePresenter.class.getSimpleName();
    private HomeContract.FragmentView fragmentView;
    private HomeContract.ActivityView activityView;

    public HomePresenter(HomeContract.FragmentView fragmentView, HomeContract.ActivityView activityView) {
        this.fragmentView = fragmentView;
        this.activityView = activityView;
    }

    @Override
    public void setFragmentView(HomeContract.FragmentView fragmentView) {
        Log.d(TAG, "setFragmentView: start");
        this.fragmentView = fragmentView;
    }

    @Override
    public void replaceFragment(Fragment fragment, String tagName) {
        activityView.replaceFragment(fragment, tagName);
    }
}
