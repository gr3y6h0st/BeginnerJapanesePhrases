package com.android.beginnerleveljapanese;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public static final int ID_TRANSLATOR_LOADER = 19;
    private FirebaseAnalytics mFirebaseAnalytics;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    @BindView(R.id.container) ViewPager mViewPager;
    @BindView(R.id.tabs) TabLayout tabLayout;
    //@BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Firebase Analytics Instance
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                mSectionsPagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    @Override
    protected void onStart() {
        //load up SharedPref everytime user re-enteres/restarts app
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String key = this.getResources().getString(R.string.sort_key);
        if (key.equals(getString(R.string.sort_key))) {
            String value = String.valueOf(sharedPreferences.getBoolean(key,
                    Boolean.parseBoolean(getResources().getString(R.string.dark_theme_default))));
            switch (value) {
                case "false": {
                    AppCompatDelegate
                            .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    System.out.println(" changed preference to " + value);
                    break;
                }
                case "true": {
                    AppCompatDelegate
                            .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    System.out.println(" changed preference to " + value);
                    break;
                }
            }
        }
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int menuItemClicked = item.getItemId();

        if (menuItemClicked == R.id.action_settings) {
            Intent settingsActivityIntent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(settingsActivityIntent);
            return true;
        } else if (menuItemClicked == R.id.menu_search_icon){
            onSearchRequested();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.sort_key))) {
            String value = String.valueOf(sharedPreferences.getBoolean(key,
                    Boolean.parseBoolean(getResources().getString(R.string.dark_theme_default))));
            switch (value) {
                case "false": {
                    AppCompatDelegate
                            .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    System.out.println(" changed preference to " + value);
                    break;
                }
                case "true": {

                    AppCompatDelegate
                            .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    System.out.println(" changed preference to " + value);
                    break;
                }
            }
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return MainActivityFragment.newInstance(position + 1);
        }
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
