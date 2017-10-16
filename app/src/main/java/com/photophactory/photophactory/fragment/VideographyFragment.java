package com.photophactory.photophactory.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.photophactory.photophactory.R;
import com.photophactory.photophactory.databinding.VideographyContentFragmentBinding;

/**
 * Created by rajaSaboor on 10/7/2017.
 */

public class VideographyFragment extends Fragment {
    private static final String TAG = VideographyFragment.class.getSimpleName();
    private VideographyContentFragmentBinding videographyContentFragmentBinding;

    public static VideographyFragment getInstance() {
        return new VideographyFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: start");
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: end");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: start");
        videographyContentFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.videography_content_fragment, container, false);
        Log.d(TAG, "onCreateView: end");
        return videographyContentFragmentBinding.getRoot();
    }
}
