package com.netforceinfotech.tagalong;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.netforceinfotech.tagalong.dashboard.MyDashboardActivity;
import com.netforceinfotech.tagalong.home.HomeActivity;
import com.netforceinfotech.tagalong.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
//comment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                finish();
            }
        }, 2000);
    }
}
