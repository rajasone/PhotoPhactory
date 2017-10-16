package com.photophactory.photophactory.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.photophactory.photophactory.BuildConfig;
import com.photophactory.photophactory.R;
import com.photophactory.photophactory.databinding.ActivityHomeBinding;
import com.photophactory.photophactory.fragment.PhotographyFragment;
import com.photophactory.photophactory.fragment.PublisherFragment;
import com.photophactory.photophactory.fragment.RetailerFragment;
import com.photophactory.photophactory.fragment.VideographyFragment;

import java.util.Arrays;

public class HomeActivity extends AppCompatActivity implements HomeContract.ActivityView, AdapterView.OnItemClickListener, FragmentManager.OnBackStackChangedListener {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private ActivityHomeBinding homeBinding;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private HomeContract.Presenter presenter;
    private int currentFragmentState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setSupportActionBar(homeBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        boolean showHomeFragment = savedInstanceState == null;
        Log.d(TAG, "onCreate: " + (showHomeFragment ? "SHOW the Home fragment" : "HIDE the Home Fragment"));
        setUpNavigationView();
        setUpDrawer();
        homeBinding.drawer.setOnItemClickListener(this);
        getSupportFragmentManager().addOnBackStackChangedListener(this);

        if (savedInstanceState != null) {
            setCurrentFragmentState(savedInstanceState.getInt(BuildConfig.CURRENT_FRAGMENT, 0));
        }

        HomeFragment homeFragment = HomeFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .add(R.id.home_fragment_container, homeFragment, HomeFragment.class.getSimpleName())
                .addToBackStack(HomeFragment.class.getSimpleName());

        Log.d(TAG, "onCreate: Show Home Fragment ---> " + showHomeFragment);
        Log.d(TAG, "onCreate: Current Fragment State ---> " + getCurrentFragmentState());
        if ((!showHomeFragment) && (getCurrentFragmentState() != HomeFragment.HOME_FRAGMENT)) {
            Log.e(TAG, "onCreate: HIDING THE FRAGMENT");
            transaction.hide(homeFragment);
        }
        transaction.commit();
        homeFragment.setPresenter(new HomePresenter(homeFragment, this));

        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate: Fragment STATE ------> " + getCurrentFragmentState());
            Fragment fragment = checkCurrentFragmentAndInflate(getCurrentFragmentState());
            if (fragment instanceof PhotographyFragment) {
                presenter = new HomePresenter(((PhotographyFragment) fragment), this);
                ((PhotographyFragment) fragment).setPresenter(presenter);
            } else if (fragment instanceof VideographyFragment) {
                Log.d(TAG, "onCreate: load the video graphy fragment");
            }
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: Saving teh fragment state ----> " + getCurrentFragmentState());
        Log.d(TAG, "onSaveInstanceState: Fragment stack count ----> " + getSupportFragmentManager().getBackStackEntryCount());
        outState.putInt(BuildConfig.CURRENT_FRAGMENT, getCurrentFragmentState());
    }

    private Fragment checkCurrentFragmentAndInflate(int currentFragment) {

        switch (currentFragment) {
            case HomeFragment.PHOTOGRAPHY_FRAGMENT:
                return addFragmentInMainContainer(PhotographyFragment.getInstance(), PhotographyFragment.class.getSimpleName());
            case HomeFragment.VIDEOGRAPHY_FRAGMENT:
                return addFragmentInMainContainer(VideographyFragment.getInstance(), VideographyFragment.class.getSimpleName());
            case HomeFragment.RETAILER_FRAGMENT:
                return addFragmentInMainContainer(RetailerFragment.getInstance(), RetailerFragment.class.getSimpleName());
            case HomeFragment.PUBLISHER_FRAGMENT:
                return addFragmentInMainContainer(PublisherFragment.getInstance(), PublisherFragment.class.getSimpleName());
        }
        return null;
    }

    public int getCurrentFragmentState() {
        return currentFragmentState;
    }

    public void setCurrentFragmentState(int currentFragmentState) {
        this.currentFragmentState = currentFragmentState;
        Log.e(TAG, "setCurrentFragmentState: State Saved");
    }

    private Fragment addFragmentInMainContainer(Fragment fragment, String fragmentName) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.home_fragment_container, fragment, fragmentName)
                .addToBackStack(fragmentName)
                .commit();

        return fragment;
    }

    private void setUpNavigationView() {
        ArrayAdapter<String> navigationAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Arrays.asList(BuildConfig.LIST_ITEMS));
        homeBinding.drawer.setAdapter(navigationAdapter);
        homeBinding.drawer.setOnItemClickListener(this);
    }

    private void setUpDrawer() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, homeBinding.drawerParent, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.navigation_string);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(getString(R.string.title_activity_home));
                invalidateOptionsMenu();
            }
        };
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBarDrawerToggle.setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.drawer_icon));
        homeBinding.drawerParent.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: start");
        getSupportFragmentManager().popBackStack();

        if (homeBinding.drawerParent.isDrawerOpen(Gravity.START)) {
            homeBinding.drawerParent.closeDrawers();
        }

        if (getSupportFragmentManager().getBackStackEntryCount() - 1 == 0) {
            Log.d(TAG, "onBackPressed: Count in IF  ----> " + (getSupportFragmentManager().getBackStackEntryCount() - 1));
            finish();
        } else if (getSupportFragmentManager().getBackStackEntryCount() - 1 == 1) {
            Log.d(TAG, "onBackPressed: show home fragment");
            setCurrentFragmentState(HomeFragment.HOME_FRAGMENT);
            Log.d(TAG, "onBackPressed: Count in ELSE IF  ----> " + (getSupportFragmentManager().getBackStackEntryCount() - 1));

            String name = getSupportFragmentManager().getBackStackEntryAt(0).getName();
            Log.d(TAG, "onBackPressed: Name ----> " + name);
            HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(name);

            if (homeFragment != null) {
                Log.d(TAG, "onBackPressed: Home fragment is GOOD");
                getSupportFragmentManager().beginTransaction().show(homeFragment).commit();
            } else {
                Log.e(TAG, "onBackPressed: Home fragment is NULL");
            }
        } else {
            Log.d(TAG, "onBackPressed: Count in ELSE  ----> " + (getSupportFragmentManager().getBackStackEntryCount() - 1));
        }
        Log.d(TAG, "onBackPressed: end");
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, BuildConfig.LIST_ITEMS[i], Toast.LENGTH_SHORT).show();
        homeBinding.drawerParent.closeDrawers();
    }

    @Override
    public void replaceFragment(Fragment fragmentToPlace, String fragmentName) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_fragment_container, fragmentToPlace)
                .addToBackStack(fragmentName)
                .commit();
    }

    @Override
    public void onBackStackChanged() {
        Log.d(TAG, "onBackStackChanged: Stack Changed");
        Log.e(TAG, "onBackStackChanged: Count ----> " + (getSupportFragmentManager().getBackStackEntryCount() - 1));

        for (int i = getSupportFragmentManager().getBackStackEntryCount() - 1; i >= 0; i--) {
            Log.e(TAG, "onBackStackChanged: Name ----> " + getSupportFragmentManager().getBackStackEntryAt(i));
            Log.d(TAG, "onBackStackChanged: ----------------");
        }
        Log.d(TAG, "onBackStackChanged: end");
    }
}
