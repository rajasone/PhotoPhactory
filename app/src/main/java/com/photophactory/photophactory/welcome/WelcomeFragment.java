package com.photophactory.photophactory.welcome;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.photophactory.photophactory.BuildConfig;
import com.photophactory.photophactory.R;
import com.photophactory.photophactory.databinding.WelcomeFragmentBinding;

/**
 * Created by rajaSaboor on 9/30/2017.
 */

public class WelcomeFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener, WelcomeContract.FragmentView {
    public static final String TAG = WelcomeFragment.class.getSimpleName();
    private WelcomeFragmentBinding welcomeFragmentBinding;
    private WelcomeContract.Presenter presenter;

    public static WelcomeFragment getInstance() {
        Log.d(TAG, "getInstance: start");
        return new WelcomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: start");
        welcomeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.welcome_fragment, container, false);
        PagerAdapter adapter = new WelcomePagerAdapter(getActivity().getSupportFragmentManager());
        welcomeFragmentBinding.viewPagerParent.setAdapter(adapter);
        welcomeFragmentBinding.viewPagerParent.addOnPageChangeListener(this);
        welcomeFragmentBinding.includeLayout.setHandler(this);
        addDots(0);
        Log.d(TAG, "onCreateView: end");
        return welcomeFragmentBinding.getRoot();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position == BuildConfig.TOTAL_TABS - 1) {
            welcomeFragmentBinding.includeLayout.skipButton.setVisibility(View.INVISIBLE);
            welcomeFragmentBinding.includeLayout.nextButton.setText("Start");
        } else {
            welcomeFragmentBinding.includeLayout.skipButton.setVisibility(View.VISIBLE);
            welcomeFragmentBinding.includeLayout.nextButton.setText("Next");
        }
    }

    @Override
    public void onPageSelected(int position) {
        Log.d(TAG, "onPageSelected: start  ---> " + position);
        addDots(position);
        Log.d(TAG, "onPageSelected: end");
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setPresenter(WelcomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private void addDots(int currentPosition) {
        if (welcomeFragmentBinding.includeLayout.dotsContainer != null) {
            welcomeFragmentBinding.includeLayout.dotsContainer.removeAllViews();
            Log.d(TAG, "addDots: Removed All VIEWS");
        }

        ImageView[] dots = new ImageView[BuildConfig.TOTAL_TABS];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(getContext());

            if (i == currentPosition) {
                dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dots));
            } else {
                dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.inactive_dots));
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(4, 0, 4, 0);
            welcomeFragmentBinding.includeLayout.dotsContainer.addView(dots[i], layoutParams);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.skip_button:
                presenter.skipTutorialActivity();
                break;
            case R.id.next_button:
                presenter.nextTutorialButton(welcomeFragmentBinding.viewPagerParent.getCurrentItem() + 1);
                break;
        }
    }

    @Override
    public void nextSlide(int slideIndex) {
        welcomeFragmentBinding.viewPagerParent.setCurrentItem(slideIndex);
    }
}
