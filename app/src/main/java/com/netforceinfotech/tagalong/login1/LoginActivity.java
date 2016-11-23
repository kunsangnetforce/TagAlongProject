package com.netforceinfotech.tagalong.login1;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.netforceinfotech.tagalong.home1.HomeActivity;
import com.netforceinfotech.tagalong.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView signupTextView;
    private TextView forgotPasswordtextView;
    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitVal();
    }

    private void InitVal() {
        loginButton = (Button) findViewById(R.id.loginButton);
        signupTextView = (TextView) findViewById(R.id.signupTextView);
        forgotPasswordtextView = (TextView) findViewById(R.id.forgotPasswordtextView);
        signupTextView.setOnClickListener(this);
        forgotPasswordtextView.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        signupTextView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.signupTextView) {

            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.enter, R.anim.exit);

        }
        if (v.getId() == R.id.forgotPasswordtextView) {

            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.enter, R.anim.exit);
        }
        if (v.getId() == R.id.loginButton) {

            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.enter, R.anim.exit);
        }
        if (v.getId() == R.id.facebookLayout) {

            Toast.makeText(getApplicationContext(), "FAcebook", Toast.LENGTH_LONG).show();
        }

    }
}
