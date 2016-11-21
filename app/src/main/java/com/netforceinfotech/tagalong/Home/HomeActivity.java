package com.netforceinfotech.tagalong.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.netforceinfotech.tagalong.DriverProfile.DriverProfile;
import com.netforceinfotech.tagalong.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Context context;
    private NavigationView navigationView;
    private Menu menu;
    DrawerLayout drawerLayout;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = this;
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        setupToolBar(getString(R.string.home));
        setupNavigationView();
        setupTab();
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
                showMessage("Chat method called");
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
        final PagerAdapterDashboard adapter = new PagerAdapterDashboard
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
                        intent = new Intent(context, DriverProfile.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.enter, R.anim.exit);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
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

    /* private void setupNavigationBar() {
         PrimaryDrawerItem myProfile = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.my_profile).withIcon(R.drawable.ic_default);
         PrimaryDrawerItem myCar = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.my_car).withIcon(R.drawable.ic_car);
         PrimaryDrawerItem myBooking = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.my_booking).withIcon(R.drawable.ic_booking);
         PrimaryDrawerItem myDashboard = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.my_dashboard).withIcon(R.drawable.ic_dash);
         PrimaryDrawerItem findRide = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.find_ride).withIcon(R.drawable.ic_search);
         PrimaryDrawerItem offerRide = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.offer_a_ride).withIcon(R.drawable.ic_offer);
         PrimaryDrawerItem settings = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.setting).withIcon(R.drawable.ic_setting);
         PrimaryDrawerItem howItWorks = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.how_it_work).withIcon(R.drawable.ic_help);
         PrimaryDrawerItem latestRide = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.latest_ride).withIcon(R.drawable.ic_latest);


         AccountHeader accountHeader = setupAccountHeader();
 //create the drawer and remember the `Drawer` result object
         Drawer result = new DrawerBuilder()
                 .withActivity(this)
                 .withAccountHeader(accountHeader)
                 .withToolbar(toolbar)
                 .addDrawerItems(
                         myProfile,
                         myCar, myBooking, myDashboard, findRide, offerRide, settings, howItWorks, latestRide
                 )
                 .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                     @Override
                     public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                         setupNavigationOnclick(position);
                         return false;
                     }
                 })
                 .build();
     }

     private AccountHeader setupAccountHeader() {
         // Create the AccountHeader
         AccountHeader headerResult = new AccountHeaderBuilder()
                 .withActivity(this)
                 .withSelectionListEnabledForSingleProfile(false)
                 .addProfiles(
                         new ProfileDrawerItem().withName("Name").withIcon(ContextCompat.getDrawable(context, R.drawable.ic_default))
                 ).withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                     @Override
                     public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                         return false;
                     }
                 })
                 .build();
         return headerResult;

     }

     private void setupNavigationOnclick(int position) {
         switch (position) {
             case 0:
                 break;
             case 1:
                 break;
             case 2:
                 break;
             case 3:
                 break;
             case 4:
                 break;
             case 5:
                 break;
             case 6:
                 break;
             case 7:
                 break;
             case 8:
                 break;

         }
     }

    */
    private void setupToolBar(String app_name) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(app_name);


    }
}
