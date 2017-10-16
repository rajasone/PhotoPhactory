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
import com.photophactory.photophactory.databinding.RetailerContentFragmentBinding;

/**
 * Created by rajaSaboor on 10/7/2017.
 */

public class RetailerFragment extends Fragment {
    private static final String TAG = RetailerFragment.class.getSimpleName();
    private RetailerContentFragmentBinding retailerContentFragmentBinding;

    public static RetailerFragment getInstance() {
        return new RetailerFragment();
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
        retailerContentFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.retailer_content_fragment, container, false);
        Log.d(TAG, "onCreateView: end");
        return retailerContentFragmentBinding.getRoot();
    }
}
