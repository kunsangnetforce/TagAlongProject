package com.netforceinfotech.tagalong.login;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.netforceinfotech.tagalong.home.HomeActivity;
import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.login.Validation.Validation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView signupTextView;
    private TextView forgotPasswordtextView;
    private Button loginButton;
    private LinearLayout facebookLinearLayout;
    private LoginButton facebookButton;
    private List<String> permissions;
    public CallbackManager mCallbackManager;
    Context context;
    private EditText userLoginEmailEditText,userLoginPasswordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        setContentView(R.layout.activity_login);
        InitView();


    }


    private void InitView() {


        loginButton = (Button) findViewById(R.id.loginButton);
        signupTextView = (TextView) findViewById(R.id.signupTextView);
        forgotPasswordtextView = (TextView) findViewById(R.id.forgotPasswordtextView);
        signupTextView.setOnClickListener(this);
        forgotPasswordtextView.setOnClickListener(this);
        context = this;
        loginButton.setOnClickListener(this);
        signupTextView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        facebookLinearLayout = (LinearLayout) findViewById(R.id.facebookLayout);
        facebookLinearLayout.setOnClickListener(this);
        facebookButton = (LoginButton) findViewById(R.id.login_button_fb);
        mCallbackManager = CallbackManager.Factory.create();
        permissions = new ArrayList<String>();
        permissions.add("email");
        permissions.add("user_birthday");
        facebookButton.setReadPermissions(permissions);
        facebookButton.registerCallback(mCallbackManager, mcallback);
        userLoginEmailEditText = (EditText) findViewById(R.id.userLoginEmailEditText);
        userLoginPasswordEditText = (EditText) findViewById(R.id.userLoginPasswordEditText);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }


    FacebookCallback<LoginResult> mcallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.enter, R.anim.exit);

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };

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

            ValidatenLogin();


        }
        if (v.getId() == R.id.facebookLayout) {

            facebookButton.performClick();

            showMessage("Facebook Login Clicked");
        }

    }

    private void ValidatenLogin() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null) {

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE || activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {


               String emailLogin = userLoginEmailEditText.getText().toString().trim();
                if(!emailLogin.isEmpty()){

                    if(isValidEmail(emailLogin)){
                          if(!userLoginPasswordEditText.getText().toString().trim().isEmpty()){

                              showMessage("Validation success");
                              Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                              startActivity(intent);
                              overridePendingTransition(R.anim.enter, R.anim.exit);


                          }else {

                              showMessage("Please Enter your password");
                          }

                    }else {
                        showMessage("Enter Valid Email");
                    }
                }else {

                    showMessage("Enter your Email Address");
                }


            }
        }
        else{
            showMessage("No Internet Connection");
        }
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void showMessage(String s) {

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
