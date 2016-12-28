package com.netforceinfotech.tagalong.home.findride.ride_available;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.chat.MyChatActivity;
import com.netforceinfotech.tagalong.home.findride.FindRideFragment;
import com.netforceinfotech.tagalong.home.findride.applyfilter.ApplyFilterActivity;

import java.util.ArrayList;

public class RidesActivity extends AppCompatActivity {

    Context context;
    Toolbar toolbar;
    private Intent intent;
    ArrayList<MyData> myDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rides);
        context=this;
        setupToolBar(getString(R.string.rides_available));
        if(FindRideFragment.mydata.size()!=0)
        setupRecyclerView(FindRideFragment.mydata);
        else Log.e("null_findride",FindRideFragment.mydata.size()+"");
    }


    private void setupRecyclerView(ArrayList<MyData> mydata) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);

      //MyData mydata=new MyData("userid","userimageurl","username","ride_price","sourceaddress","destinationaddress","departuredata","departuretime",2);
        //myDatas.add(mydata);
        MyAdapter myAdapter = new MyAdapter(context, mydata);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);
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
        getMenuInflater().inflate(R.menu.ride_available, menu);
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
            case R.id.itemFilter:
                intent=new Intent(context, ApplyFilterActivity.class);
                startActivity(intent);
                return true;
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

}
