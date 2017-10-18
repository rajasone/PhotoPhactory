package com.photophactory.photophactory.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.photophactory.photophactory.R;
import com.photophactory.photophactory.databinding.HomeDefaultFragmentBinding;
import com.photophactory.photophactory.fragment.PhotographyFragment;
import com.photophactory.photophactory.fragment.PublisherFragment;
import com.photophactory.photophactory.fragment.RetailerFragment;
import com.photophactory.photophactory.fragment.VideographyFragment;

/**
 * Created by rajaSaboor on 10/6/2017.
 */

public class HomeFragment extends Fragment implements HomeContract.FragmentView, View.OnClickListener {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private HomeDefaultFragmentBinding homeFragmentBinding;
    private HomeContract.Presenter presenter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
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
        homeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.home_default_fragment, container, false);
        homeFragmentBinding.setHomeHandler(this);
        Log.d(TAG, "onCreateView: end");
        return homeFragmentBinding.getRoot();
    }

    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDetach() {
        Log.e(TAG, "onDetach: start");
        super.onDetach();
        Log.e(TAG, "onDetach: end");
    }

    @Override
    public void onDestroyView() {
        Log.e(TAG, "onDestroyView: start");
        super.onDestroyView();
        Log.e(TAG, "onDestroyView: end");
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.photography_home_parent:
                showToast("Add photo fragment");
                ((HomeActivity) getActivity()).replaceFragment(PhotographyFragment.getInstance(), PhotographyFragment.class.getSimpleName());
                break;
            case R.id.videography_home_parent:
                showToast("Add video fragment");
                ((HomeActivity) getActivity()).replaceFragment(VideographyFragment.getInstance(), VideographyFragment.class.getSimpleName());
                break;
            case R.id.retailer_home_parent:
                showToast("Add retailer fragment");
                ((HomeActivity) getActivity()).replaceFragment(RetailerFragment.getInstance(), RetailerFragment.class.getSimpleName());
                break;
            case R.id.publisher_home_parent:
                showToast("Add publisher fragment");
                ((HomeActivity) getActivity()).replaceFragment(PublisherFragment.getInstance(), PublisherFragment.class.getSimpleName());
                break;
        }
    }
}
