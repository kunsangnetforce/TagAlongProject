package com.netforceinfotech.tagalong.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.chat.MyChatActivity;
import com.netforceinfotech.tagalong.dashboard.MyDashboardActivity;
import com.netforceinfotech.tagalong.driverProfile.DriverProfile;
import com.netforceinfotech.tagalong.home.findride.CantFindRideActivity;
import com.netforceinfotech.tagalong.myCars.MyCarActivity;
import com.netforceinfotech.tagalong.myCars.carlist.CarListActivity;
import com.netforceinfotech.tagalong.myprofile.MyProfileActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Context context;
    private NavigationView navigationView;
    private Menu menu;
    DrawerLayout drawerLayout;
    private Intent intent;
    private DatabaseReference mDatabase;
    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = this;
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        testFirebase();
        setupToolBar(getString(R.string.home).toUpperCase());
        setupNavigationView();
        setupTab();


        //firebase testing
        mFirebaseAuth = FirebaseAuth.getInstance();


//        Log.e("mDatabase.getRoot()", mDatabase.getRoot().toString());

        if (true) {
           // showReviewPopUp();
        }
    }

    private void testFirebase() {
        DatabaseReference _public = FirebaseDatabase.getInstance().getReference().child("public");
        Map<String, Object> map = new HashMap<>();
        map.put("id", "123");
        _public.updateChildren(map);
    }

    private void showReviewPopUp() {
        new MaterialDialog.Builder(this)
                .customView(R.layout.review_pop_up, false)
                .positiveText(getString(R.string.submit))
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        showMessage("Submit review");
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void setupToolBar(String app_name) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(app_name);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemChat:
                intent = new Intent(context, MyChatActivity.class);
                startActivity(intent);
                return true;
            case R.id.itemNotification:
                // Exit option clicked.
                showMessage("Notification method called");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void setupTab() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.find_a_ride_cap));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.offer_a_ride_cap));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapterHome adapter = new PagerAdapterHome
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


     /*   tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void setupNavigationView() {
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        final List<MenuItem> items = new ArrayList<>();

        menu = navigationView.getMenu();
        menu.add(getString(R.string.my_profile)).setIcon(R.drawable.ic_user);
        menu.add(getString(R.string.my_car)).setIcon(R.drawable.ic_car);
        menu.add(getString(R.string.my_booking)).setIcon(R.drawable.ic_booking);
        menu.add(getString(R.string.my_dashboard)).setIcon(R.drawable.ic_dash);
        menu.add(getString(R.string.find_ride)).setIcon(R.drawable.ic_search);
        menu.add(getString(R.string.offer_a_ride)).setIcon(R.drawable.ic_offer);
        menu.add(getString(R.string.setting)).setIcon(R.drawable.ic_setting);
        menu.add(getString(R.string.how_it_work)).setIcon(R.drawable.ic_help);
        menu.add(getString(R.string.latest_ride)).setIcon(R.drawable.ic_latest);


        for (int i = 0; i < menu.size(); i++) {
            items.add(menu.getItem(i));
        }
        menu.setGroupCheckable(0, true, false);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Checking if the item is in checked state or not, if not make it in checked state
                menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();
                //Check to see which item was being clicked and perform appropriate action
                switch (items.indexOf(menuItem)) {
                    case 0:
                        intent = new Intent(context, MyProfileActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.enter, R.anim.exit);
                        break;
                    case 1:
                        intent = new Intent(context, CarListActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.enter, R.anim.exit);
                        break;
                    case 2:
                        break;
                    case 3:
                        intent = new Intent(context, MyDashboardActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.enter, R.anim.exit);
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        intent = new Intent(context, CantFindRideActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.enter, R.anim.exit);
                        break;
                    case 8:
                        intent = new Intent(context, DriverProfile.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.enter, R.anim.exit);
                        break;

                    default:


                }
                return false;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }

        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }


}
