package com.netforceinfotech.tagalong.home.offerride;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.DatePicker;
import android.widget.Toast;

import com.netforceinfotech.tagalong.R;


import java.util.Calendar;
import java.util.Date;

public class RideDetailActivity extends AppCompatActivity implements View.OnClickListener {

    boolean oneway = true;
    LinearLayout linearLayoutRoundTrip;
    private int mYear, mMonth, mDay, mHour, mMinute;
    TextView textViewTitle;
    Context context;
    Toolbar toolbar;
    String rideDetail;
    private Intent intent;
    private Bundle bundle;
    private EditText departureDateEditText, departureTimeEditText, returnDateEditText, returnTimeEditText;
    EditText toEditText,fromEditText,priceEditText,stopoverEditText,returnstopoverEditText;



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

    private void initView() {
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        linearLayoutRoundTrip = (LinearLayout) findViewById(R.id.linearLayoutRoundTrip);
        if (oneway) {
            linearLayoutRoundTrip.setVisibility(View.GONE);
        } else {
            textViewTitle.setText(getString(R.string.round_trip));
        }
        findViewById(R.id.textViewNext).setOnClickListener(this);

        departureDateEditText = (EditText) findViewById(R.id.departureEditText);
        departureDateEditText.setOnClickListener(this);
        departureTimeEditText = (EditText) findViewById(R.id.departTimeEditText);
        departureTimeEditText.setOnClickListener(this);
        returnDateEditText = (EditText) findViewById(R.id.returnDateEditText);
        returnDateEditText.setOnClickListener(this);
        returnTimeEditText = (EditText) findViewById(R.id.returnTimeEditText);
        returnTimeEditText.setOnClickListener(this);
        fromEditText = (EditText) findViewById(R.id.fromEditText);
        toEditText = (EditText) findViewById(R.id.toEditText);
        stopoverEditText = (EditText) findViewById(R.id.stopoverEditText);
        returnstopoverEditText = (EditText) findViewById(R.id.returnStopOverEditText);


    }

    private void setupToolbar(String s) {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(s.toUpperCase());

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


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewNext:
                intent = new Intent(context, CarDetailActivity.class);
                bundle = new Bundle();
                bundle.putBoolean("oneway", oneway);
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                break;
            case R.id.departureEditText:
                showDepartDate();
                break;
            case R.id.departTimeEditText:
                showTime();
                break;
            case R.id.returnDateEditText:
                showReturnDate();
                break;
            case R.id.returnTimeEditText:
                showReturnTime();


        }
    }

    private void showReturnTime() {

        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,R.style.DialogTheme,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        returnTimeEditText.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    private void showMessage(String s) {

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private void showReturnDate() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        returnDateEditText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();


    }

    private void showTime() {


        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,R.style.DialogTheme,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        departureTimeEditText.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }





    private void showDepartDate() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        departureDateEditText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();


    }


}
