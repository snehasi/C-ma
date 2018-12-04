package com.donut.cma;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity
        implements HomeFragment.OnFragmentInteractionListener,
        CropsFragment.OnFragmentInteractionListener,
        HelplineFragment.OnFragmentInteractionListener,
        MarketFragment.OnFragmentInteractionListener,
        AlertsFragment.OnFragmentInteractionListener {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.helpline:
                    fragment = new HelplineFragment();
                    break;
                case R.id.crops:
                    fragment = new CropsFragment();
                    break;
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    break;
                case R.id.market_prices:
                    fragment = new MainFragment();
                    break;
                case R.id.alerts:
                    fragment = new AlertsFragment();
                    break;
            }
            return loadFragment(fragment);
        }
    };

    private Boolean loadFragment(Fragment fragment)
    {
        if (fragment != null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loadFragment(new HomeFragment());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigation.getMenu().getItem(2).setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        MenuItem item = menu.findItem(R.id.action_switch);
        item.setActionView(R.layout.switch_button);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
