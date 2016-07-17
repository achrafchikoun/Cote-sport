package com.achraf.sport.cote.cotesport;


import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.achraf.sport.cote.cotesport.Fragments.AutresFragment;
import com.achraf.sport.cote.cotesport.Fragments.BasketballFragment;
import com.achraf.sport.cote.cotesport.Fragments.ContactFragment;
import com.achraf.sport.cote.cotesport.Fragments.FootballFragment;
import com.achraf.sport.cote.cotesport.Fragments.VoteFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        FootballFragment.OnFragmentInteractionListener,
        BasketballFragment.OnFragmentInteractionListener,
        AutresFragment.OnFragmentInteractionListener,
        ContactFragment.OnFragmentInteractionListener,
        VoteFragment.OnFragmentInteractionListener{

    Fragment fragment;
    Toolbar toolbar;
    FragmentTransaction transaction;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        setTitle(this.getResources().getString(R.string.football));
        initDrawerNavigation();
        fragment = new FootballFragment();
        setFragmentManager(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
       /* int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    /**** Drawer navigation start ****/
    public void initDrawerNavigation() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_football);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch(item.getItemId()){
            case R.id.nav_football :
                if(!(fragment instanceof  FootballFragment)){
                    fragment = new FootballFragment();
                    setFragmentManager(fragment);
                }
                break;

            case R.id.nav_basketball :
                if(!(fragment instanceof  BasketballFragment)) {
                    fragment = new BasketballFragment();
                    setFragmentManager(fragment);
                }
                break;

            case R.id.nav_autres :
                if(!(fragment instanceof  AutresFragment)) {
                    fragment = new AutresFragment();
                    setFragmentManager(fragment);
                }
                break;

            case R.id.nav_contact :
                if(!(fragment instanceof ContactFragment)) {
                    fragment = new ContactFragment();
                    setFragmentManager(fragment);
                }
                break;

            case R.id.nav_vote :
                if(!(fragment instanceof VoteFragment)) {
                    fragment = new VoteFragment();
                    setFragmentManager(fragment);
                }
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**** Drawer navigation end ****/

    /**** Setting fragment manager ****/

    public void setFragmentManager(Fragment fragment){
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    /**** Setting fragment manager end ****/
}
