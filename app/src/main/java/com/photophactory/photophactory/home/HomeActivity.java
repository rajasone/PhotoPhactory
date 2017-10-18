package com.photophactory.photophactory.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setSupportActionBar(homeBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        setUpNavigationView();
        setUpDrawer();
        homeBinding.drawer.setOnItemClickListener(this);
        getSupportFragmentManager().addOnBackStackChangedListener(this);

        if (savedInstanceState == null) {
            Log.d(TAG, "onCreate: bundle is NULL");
            HomeFragment homeFragment = HomeFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.home_fragment_container, homeFragment, HomeFragment.class.getSimpleName())
                    .commitNow();
            homeFragment.setPresenter(new HomePresenter(homeFragment, this));
        }

        /*
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.home_fragment_container);
        if (fragment instanceof HomeFragment) {
            Log.e(TAG, "onCreate: Instance of Home Fragment");
            ((HomeFragment) fragment).setPresenter(new HomePresenter(((HomeFragment) fragment), this));
        } else if (fragment instanceof PhotographyFragment) {
            Log.d(TAG, "onCreate: Instance of Photography fragment");
            ((PhotographyFragment) fragment).setPresenter(new HomePresenter(((PhotographyFragment) fragment), this));
        } else if (fragment instanceof VideographyFragment) {
            Log.e(TAG, "onCreate: Instance of videography fragment");
        } else if (fragment instanceof RetailerFragment) {
            Log.e(TAG, "onCreate: Instance of retailer fragment");
        } else if (fragment instanceof PublisherFragment) {
            Log.e(TAG, "onCreate: Instance of publisher fragment");
        } else {
            Log.e(TAG, "onCreate: Fragment in ELSE");
        }
        */
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
                getSupportActionBar().setTitle(getString(R.string.app_name));
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, BuildConfig.LIST_ITEMS[i], Toast.LENGTH_SHORT).show();
        homeBinding.drawerParent.closeDrawers();
    }

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
