package com.netforceinfotech.tagalong.home1.offerride;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.netforceinfotech.tagalong.R;

public class CarDetailActivity extends AppCompatActivity {

    boolean oneway = true;
    String rideDetail;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);
        try {
            Bundle bundle = getIntent().getExtras();
            oneway = bundle.getBoolean("oneway");
        } catch (Exception ex) {

        }
        if (oneway) {
            rideDetail = getString(R.string.one_way_trip);
        } else {
            rideDetail = getString(R.string.round_trip);
        }
        setupToolbar(rideDetail);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupToolbar(String s) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(s);

    }
}
