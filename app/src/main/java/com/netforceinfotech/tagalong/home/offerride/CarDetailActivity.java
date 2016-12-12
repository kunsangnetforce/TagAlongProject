package com.netforceinfotech.tagalong.home.offerride;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class CarDetailActivity extends AppCompatActivity implements View.OnClickListener {

    boolean oneway = true;
    String rideDetail;
    Toolbar toolbar;
    private ImageView iwillleaveImageView;
    private DroppyMenuPopup.Builder carlistBuilder;
    LinearLayout linearLayoutCar;
    TextView textViewCar;
    ArrayList<DummyData> dummyDatas = new ArrayList<>();


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

        textViewCar= (TextView) findViewById(R.id.textViewCar);
        linearLayoutCar = (LinearLayout) findViewById(R.id.linearLayoutCarList);
        linearLayoutCar.setOnClickListener(this);
        iwillleaveImageView = (ImageView) findViewById(R.id.iwillLeaveImageView);
        iwillleaveImageView.setOnClickListener(this);
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

        if (v.getId() == R.id.iwillLeaveImageView) {

            showIwillLeaveDDL();
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


}
