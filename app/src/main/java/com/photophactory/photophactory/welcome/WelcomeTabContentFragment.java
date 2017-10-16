package com.photophactory.photophactory.welcome;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.photophactory.photophactory.R;

import static com.photophactory.photophactory.BuildConfig.PHOTOGRAPHY_TAB;
import static com.photophactory.photophactory.BuildConfig.PUBLISHERS_TAB;
import static com.photophactory.photophactory.BuildConfig.RETAILERS_TAB;
import static com.photophactory.photophactory.BuildConfig.TAB_KEY;
import static com.photophactory.photophactory.BuildConfig.VIDEOGRAPHY_TAB;

/**
 * Created by rajaSaboor on 9/30/2017.
 */

public class WelcomeTabContentFragment extends Fragment {
    private static final String TAG = WelcomeTabContentFragment.class.getSimpleName();

    public static WelcomeTabContentFragment newInstance(int position) {
        WelcomeTabContentFragment welcomeFragment = new WelcomeTabContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TAB_KEY, position);
        welcomeFragment.setArguments(bundle);
        return welcomeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: start");
        int temp = 0;

        if (getArguments() != null) {
            temp = getArguments().getInt(TAB_KEY);
        }
        return setLayout(temp, inflater, container);
    }

    private View setLayout(int position, LayoutInflater inflater, ViewGroup container) {
        switch (position) {
            case PHOTOGRAPHY_TAB:
                return DataBindingUtil.inflate(inflater, R.layout.photography_slide_layout, container, false).getRoot();
            case VIDEOGRAPHY_TAB:
                return DataBindingUtil.inflate(inflater, R.layout.videography_slide_layout, container, false).getRoot();
            case PUBLISHERS_TAB:
                return DataBindingUtil.inflate(inflater, R.layout.publishers_slide_layout, container, false).getRoot();
            case RETAILERS_TAB:
                return DataBindingUtil.inflate(inflater, R.layout.retailers_slide_layout, container, false).getRoot();
            default:
                return DataBindingUtil.inflate(inflater, R.layout.photography_slide_layout, container, false).getRoot();
        }
    }
}
