package com.netforceinfotech.tagalong.home.offerride;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.tagalong.R;
import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.animations.DroppyFadeInAnimation;

import java.util.ArrayList;

import static com.netforceinfotech.tagalong.R.id.relativeCar;

public class CarDetailActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    int SMALL = 0, MEDIUM = 1, LARGE = 2;
    private static int incredecreCount = 0;
    private String counterStringVal;
    boolean oneway = true;
    String rideDetail;
    Toolbar toolbar;
    private ImageView iwillleaveImageView;
    private DroppyMenuPopup.Builder carlistBuilder;
    LinearLayout linearLayoutCar;
    TextView textViewCar;
    ArrayList<DummyData> dummyDatas = new ArrayList<>();
    private ImageView smallImageView, mediumImageView, largeImageView;
    RadioButton radioButtonSmall, radioButtonMedium, radioButtonLarge;
    int selectedType = SMALL;
    private TextView textViewMinus, textViewPlus, inCredeCreTextView;


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
        initView();
    }

    private void initView() {
        smallImageView = (ImageView) findViewById(R.id.smallImaveView);
        mediumImageView = (ImageView) findViewById(R.id.mediumImageView);
        largeImageView = (ImageView) findViewById(R.id.largeImageView);
        radioButtonLarge = (RadioButton) findViewById(R.id.radioButtonlarge);
        radioButtonSmall = (RadioButton) findViewById(R.id.radioButtonsmall);
        radioButtonMedium = (RadioButton) findViewById(R.id.radioButtonmedium);
        radioButtonLarge.setOnCheckedChangeListener(this);
        radioButtonMedium.setOnCheckedChangeListener(this);
        radioButtonSmall.setOnCheckedChangeListener(this);
        smallImageView.setOnClickListener(this);
        largeImageView.setOnClickListener(this);
        mediumImageView.setOnClickListener(this);

        smallImageView.performClick();

        textViewCar = (TextView) findViewById(R.id.textViewCar);
        linearLayoutCar = (LinearLayout) findViewById(R.id.linearLayoutCarList);
        linearLayoutCar.setOnClickListener(this);
        iwillleaveImageView = (ImageView) findViewById(R.id.iwillLeaveImageView);
        iwillleaveImageView.setOnClickListener(this);
        textViewMinus = (TextView) findViewById(R.id.textViewMinus);
        textViewPlus = (TextView) findViewById(R.id.textViewPlus);
        inCredeCreTextView = (TextView) findViewById(R.id.InCreDeCreTextView);
        textViewMinus.setOnClickListener(this);
        textViewPlus.setOnClickListener(this);

        setupCarDropdown();


    }

    private void setupCarDropdown() {
        // null pointer exception...

        getReadyCarDatas();
        // carlistBuilder = new DroppyMenuPopup.Builder(this, linearLayoutCar);


        carlistBuilder.setOnClick(new DroppyClickCallbackInterface() {
            @Override
            public void call(View v, int id) {
                Toast.makeText(CarDetailActivity.this, dummyDatas.get(id).title, Toast.LENGTH_SHORT).show();
                textViewCar.setText(dummyDatas.get(id).title);
            }
        });

        carlistBuilder.build();
// white space shows if we follow the folowwing prgrm....
//
//       DroppyMenuPopup droppyMenu =
//                carlistBuilder.build();
//        droppyMenu.show();
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
        getSupportActionBar().setTitle(s.toUpperCase());

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.textViewMinus:

                PerformSubtraction();
                break;
            case R.id.textViewPlus:

                PerFormAddition();
                break;
            case R.id.iwillLeaveImageView:
                showIwillLeaveDDL();
                break;
            case R.id.smallImaveView:
                radioButtonSmall.setChecked(true);
                break;
            case R.id.mediumImageView:
                radioButtonMedium.setChecked(true);
                break;
            case R.id.largeImageView:
                radioButtonLarge.setChecked(true);
                break;

        }

    }

    private void PerformSubtraction() {

        int in = Integer.parseInt(inCredeCreTextView.getText().toString());

        if(in>1) {
            in = in - 1;
            counterStringVal = Integer.toString(in);
            inCredeCreTextView.setText(counterStringVal);
        } else{
            showMessage("At least need one seat");
        }

    }

    private void PerFormAddition() {
        int in = Integer.parseInt(inCredeCreTextView.getText().toString());
        if (in >= 80) {

            showMessage("Enter Appropiate Seats");
        } else {

            in = in + 1;
            Log.d("IncreValue", String.valueOf(in));
            counterStringVal = Integer.toString(in);
            inCredeCreTextView.setText(counterStringVal);
        }


    }


    public void getReadyCarDatas() {

        carlistBuilder = new DroppyMenuPopup.Builder(this, linearLayoutCar);
        Ion.with(getApplicationContext())
                .load("https://jsonplaceholder.typicode.com/posts")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {

                        if (result != null) {
                            int size = result.size();
                            for (int i = 0; i < size; i++) {

                                JsonObject jsonObject = result.get(i).getAsJsonObject();
                                //JsonObject jsob = (JsonObject) result.get(i);

                                String userId = jsonObject.get("userId").getAsString();
                                String id = jsonObject.get("id").getAsString();
                                String title = jsonObject.get("title").getAsString();
                                String body = jsonObject.get("body").getAsString();

                                DummyData dummyData = new DummyData(userId, id, title, body);


                                Log.d("Name", title);
                                if (!dummyDatas.contains(dummyData)) {
                                    dummyDatas.add(dummyData);
                                    carlistBuilder.addMenuItem(new DroppyMenuItem(dummyData.title));
                                    carlistBuilder.build();
                                }


                            }
                        }

                    }
                });

    }


    private void showMessage(String s) {

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


    private void showIwillLeaveDDL() {

        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(this, iwillleaveImageView);
        DroppyMenuPopup droppyMenu = droppyBuilder.fromMenu(R.menu.iwillleave_dropdown)
                .triggerOnAnchorClick(false)
                .setOnClick(new DroppyClickCallbackInterface() {
                    @Override
                    public void call(View v, int id) {

                        switch (id) {

                            case R.id.dd1:
                                Toast.makeText(getApplicationContext(), "IwillLeave ddl1 clicked", Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd2:
                                Toast.makeText(getApplicationContext(), "IwillLeave ddl2 clicked", Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd3:
                                Toast.makeText(getApplicationContext(), "IwillLeave ddl3 clicked", Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd4:
                                Toast.makeText(getApplicationContext(), "IwillLeave ddl4 clicked", Toast.LENGTH_LONG).show();

                                break;

                        }


                    }
                })
                .setOnDismissCallback(new DroppyMenuPopup.OnDismissCallback() {
                    @Override
                    public void call() {

                        Log.i("Hello", "sdfsdaf");
                    }
                })
                .setPopupAnimation(new DroppyFadeInAnimation())
                .setXOffset(15)
                .setYOffset(5)
                .build();
        droppyMenu.show();
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.radioButtonsmall:
                if (isChecked) {
                    selectedType = SMALL;
                    smallImageView.setImageResource(R.drawable.ic_checked_accent);
                    mediumImageView.setImageResource(R.drawable.ic_checked_grey);
                    largeImageView.setImageResource(R.drawable.ic_checked_grey);
                }
                break;
            case R.id.radioButtonmedium:
                if (isChecked) {
                    selectedType = MEDIUM;
                    smallImageView.setImageResource(R.drawable.ic_checked_grey);
                    mediumImageView.setImageResource(R.drawable.ic_checked_accent);
                    largeImageView.setImageResource(R.drawable.ic_checked_grey);
                }
                break;
            case R.id.radioButtonlarge:
                if (isChecked) {
                    selectedType = LARGE;
                    smallImageView.setImageResource(R.drawable.ic_checked_grey);
                    mediumImageView.setImageResource(R.drawable.ic_checked_grey);
                    largeImageView.setImageResource(R.drawable.ic_checked_accent);
                }
                break;
        }

    }
}
