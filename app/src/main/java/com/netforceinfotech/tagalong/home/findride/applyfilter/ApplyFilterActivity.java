package com.netforceinfotech.tagalong.home.findride.applyfilter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.chat.MyChatActivity;

public class ApplyFilterActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    RadioButton radioButtonTime, radioButtonPrice, radioButtonPictures, radioButtonRideType, radioButtonCarComfort, radioButtonRating;
    TextView textViewTime, textViewPrice, textViewPicture, textViewRideType, textViewCarComfort, textViewRating;
    private Context context;
    private String tagName = "";
    Toolbar toolbar;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_filter);
        context = this;
        initView();
        setupToolbar(getString(R.string.apply_filter));
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
            case R.id.itemChat:
                intent = new Intent(context, MyChatActivity.class);
                startActivity(intent);
                return true;
            case R.id.itemNotification:
                // Exit option clicked.
                showMessage("Notification method called");
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        findViewById(R.id.buttonApply).setOnClickListener(this);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        textViewPrice = (TextView) findViewById(R.id.textViewPrice);
        textViewPicture = (TextView) findViewById(R.id.textViewPicture);
        textViewRideType = (TextView) findViewById(R.id.textViewRideType);
        textViewCarComfort = (TextView) findViewById(R.id.textViewCarComfort);
        textViewRating = (TextView) findViewById(R.id.textViewRating);
        radioButtonTime = (RadioButton) findViewById(R.id.radioButtonTime);
        radioButtonPrice = (RadioButton) findViewById(R.id.radioButtonPrice);
        radioButtonPictures = (RadioButton) findViewById(R.id.radioButtonPicture);
        radioButtonRideType = (RadioButton) findViewById(R.id.radioButtonRideType);
        radioButtonCarComfort = (RadioButton) findViewById(R.id.radioButtonCarcomfort);
        radioButtonRating = (RadioButton) findViewById(R.id.radioButtonRating);
        radioButtonTime.setOnCheckedChangeListener(this);
        radioButtonPrice.setOnCheckedChangeListener(this);
        radioButtonPictures.setOnCheckedChangeListener(this);
        radioButtonRideType.setOnCheckedChangeListener(this);
        radioButtonCarComfort.setOnCheckedChangeListener(this);
        radioButtonRating.setOnCheckedChangeListener(this);
        //textViewTime.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        radioButtonTime.setChecked(false);
        radioButtonTime.setChecked(true);
        textViewTime.setOnClickListener(this);
        textViewCarComfort.setOnClickListener(this);
        textViewPicture.setOnClickListener(this);
        textViewPrice.setOnClickListener(this);
        textViewRating.setOnClickListener(this);
        textViewRideType.setOnClickListener(this);
    }

    private void replaceFragment(Fragment newFragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fromLayout, newFragment, tag);
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.radioButtonCarcomfort:
                if (b) {
                    textViewCarComfort.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    setupCarComfortFragment();
                } else {
                    textViewCarComfort.setTextColor(ContextCompat.getColor(context, R.color.colorTextPrimary));
                }
                break;
            case R.id.radioButtonPicture:
                if (b) {
                    textViewPicture.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    setupPictureFragment();
                } else {
                    textViewPicture.setTextColor(ContextCompat.getColor(context, R.color.colorTextPrimary));
                }
                break;
            case R.id.radioButtonPrice:
                if (b) {
                    textViewPrice.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    setupPriceFragment();
                } else {
                    textViewPrice.setTextColor(ContextCompat.getColor(context, R.color.colorTextPrimary));
                }
                break;
            case R.id.radioButtonRating:
                if (b) {
                    textViewRating.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    setupRatingFragment();
                } else {
                    textViewRating.setTextColor(ContextCompat.getColor(context, R.color.colorTextPrimary));
                }
                break;
            case R.id.radioButtonTime:
                if (b) {
                    textViewTime.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    setupTimeFragment();
                } else {
                    textViewTime.setTextColor(ContextCompat.getColor(context, R.color.colorTextPrimary));
                }
                break;
            case R.id.radioButtonRideType:
                if (b) {
                    textViewRideType.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    setupRideTypeFragment();
                } else {
                    textViewRideType.setTextColor(ContextCompat.getColor(context, R.color.colorTextPrimary));
                }
                break;
        }

    }

    private void setupRideTypeFragment() {
        RideTypeFragment rideTypeFragment = new RideTypeFragment();
        tagName = rideTypeFragment.getClass().getName();
        replaceFragment(rideTypeFragment, tagName);
    }

    private void setupTimeFragment() {
        TimeFragment timeFragment = new TimeFragment();
        tagName = timeFragment.getClass().getName();
        replaceFragment(timeFragment, tagName);
    }

    private void setupRatingFragment() {
        RatingFragment ratingFragment = new RatingFragment();
        tagName = ratingFragment.getClass().getName();
        replaceFragment(ratingFragment, tagName);
    }

    private void setupPriceFragment() {
        PriceFragment priceFragment = new PriceFragment();
        tagName = priceFragment.getClass().getName();
        replaceFragment(priceFragment, tagName);
    }

    private void setupPictureFragment() {
        PicturesFragment picturesFragment = new PicturesFragment();
        tagName = picturesFragment.getClass().getName();
        replaceFragment(picturesFragment, tagName);
    }

    private void setupCarComfortFragment() {

        CarComfortFragment carComfortFragment = new CarComfortFragment();
        tagName = carComfortFragment.getClass().getName();
        replaceFragment(carComfortFragment, tagName);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        // TextView textViewTime, textViewPrice, textViewPicture, textViewRideType, textViewCarComfort, textViewRating;
        switch (view.getId()) {
            case R.id.buttonApply:
                showMessage("Filter applied");
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

                break;
            case R.id.textViewTime:
                radioButtonTime.setChecked(true);
                break;
            case R.id.textViewCarComfort:
                radioButtonCarComfort.setChecked(true);
                break;
            case R.id.textViewPicture:
                radioButtonPictures.setChecked(true);
                break;
            case R.id.textViewPrice:
                radioButtonPrice.setChecked(true);
                break;
            case R.id.textViewRideType:
                radioButtonRideType.setChecked(true);
                break;
            case R.id.textViewRating:
                radioButtonRating.setChecked(true);
                break;

        }
    }
}
