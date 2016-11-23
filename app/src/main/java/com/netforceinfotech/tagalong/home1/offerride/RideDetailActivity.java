package com.netforceinfotech.tagalong.home1.offerride;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netforceinfotech.tagalong.R;

public class RideDetailActivity extends AppCompatActivity implements View.OnClickListener {

    boolean oneway = true;
    LinearLayout linearLayoutRoundTrip;
    TextView textViewTitle;
    Context context;
    Toolbar toolbar;
    String rideDetail;
    private Intent intent;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_detail);
        context = this;
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
        initView();
        setupToolbar(rideDetail);


    }

    private void setupToolbar(String s) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(s);

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

    private void initView() {
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        linearLayoutRoundTrip = (LinearLayout) findViewById(R.id.linearLayoutRoundTrip);
        if (oneway) {
            linearLayoutRoundTrip.setVisibility(View.GONE);
        } else {
            textViewTitle.setText(getString(R.string.round_trip));
        }
        findViewById(R.id.textViewNext).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewNext:
                intent = new Intent(context, CarDetailActivity.class);
                bundle = new Bundle();
                bundle.putBoolean("oneway", false);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                break;
        }
    }
}
