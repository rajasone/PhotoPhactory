package com.photophactory.photophactory.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.photophactory.photophactory.R;
import com.photophactory.photophactory.databinding.PhotographyContentFragmentBinding;
import com.photophactory.photophactory.home.HomeContract;

/**
 * Created by rajaSaboor on 10/7/2017.
 */

public class PhotographyFragment extends Fragment implements HomeContract.FragmentView {
    private static final String TAG = PhotographyFragment.class.getSimpleName();
    private PhotographyContentFragmentBinding photographyContentFragmentBinding;
    private HomeContract.Presenter presenter;

    public static PhotographyFragment getInstance() {
        return new PhotographyFragment();
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach: start");
        super.onAttach(context);
        Log.d(TAG, "onAttach: end");
    }

    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: start");
        photographyContentFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.photography_content_fragment, container, false);
        Log.d(TAG, "onCreateView: end");
        return photographyContentFragmentBinding.getRoot();
    }


    @Override
    public void onDetach() {
        Log.e(TAG, "onDetach: start");
        super.onDetach();
        Log.e(TAG, "onDetach: End");
    }

    @Override
    public void onDestroyView() {
        Log.e(TAG, "onDestroyView: start");
        super.onDestroyView();
        Log.e(TAG, "onDestroyView: end");
    }

    @Override
    public void showToast(String message) {

    }
}
