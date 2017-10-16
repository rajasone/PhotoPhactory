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
import com.photophactory.photophactory.databinding.PhotographyContentFragmentBinding;
import com.photophactory.photophactory.home.HomeActivity;
import com.photophactory.photophactory.home.HomeContract;
import com.photophactory.photophactory.home.HomeFragment;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: start");
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            ((HomeActivity) getActivity()).setCurrentFragmentState(HomeFragment.PHOTOGRAPHY_FRAGMENT);
        }
        Log.d(TAG, "onCreate: end");
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
    public void showToast(String message) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: Photography state saving ---> ");
        ((HomeActivity) getActivity()).setCurrentFragmentState(HomeFragment.PHOTOGRAPHY_FRAGMENT);
    }
}
