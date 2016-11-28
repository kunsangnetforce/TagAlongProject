package com.netforceinfotech.tagalong.myCars;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.chat.MyChatActivity;


public class MyCarActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private Intent intent;
    private Button addCarButton;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_car);
        Toast.makeText(this, "h", Toast.LENGTH_SHORT).show();
        setupToolbar("MY CARS");
        initView();

    }

    private void initView() {
        addCarButton = (Button) findViewById(R.id.addNewCarButton);
        addCarButton.setOnClickListener(this);

    }

    private void setupToolbar(String s) {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(s);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                return true;

            case R.id.itemChat:
                intent = new Intent(getApplicationContext(), MyChatActivity.class);
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

    public void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.addNewCarButton)
        {
            intent = new Intent(MyCarActivity.this,RegisterNewCar.class);
            startActivity(intent);
            overridePendingTransition(R.anim.left_to_right,R.anim.right_to_left);
        }



    }
}
