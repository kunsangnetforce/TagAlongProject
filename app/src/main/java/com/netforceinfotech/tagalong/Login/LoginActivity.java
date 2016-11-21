package com.netforceinfotech.tagalong.Login;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.netforceinfotech.tagalong.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView signupTextView;
    private TextView forgotPasswordtextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitVal();
    }

    private void InitVal() {

        signupTextView = (TextView) findViewById(R.id.signupTextView);
        forgotPasswordtextView= (TextView) findViewById(R.id.forgotPasswordtextView);
        signupTextView.setOnClickListener(this);
        signupTextView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.signupTextView) {

            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            overridePendingTransition(R.anim.enter, R.anim.exit);
            startActivity(intent);

        }if(v.getId()==R.id.forgotPasswordtextView){

            Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
            overridePendingTransition(R.anim.enter, R.anim.exit);
            startActivity(intent);
        }

    }
}
