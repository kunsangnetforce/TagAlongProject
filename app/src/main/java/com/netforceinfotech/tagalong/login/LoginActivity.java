package com.netforceinfotech.tagalong.login;

import android.app.ProgressDialog;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.tagalong.home.HomeActivity;
import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.login.Validation.Validation;

import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView signupTextView;
    private TextView forgotPasswordtextView;
    private Button loginButton;
    private LinearLayout facebookLinearLayout;
    private LoginButton facebookButton;
    private List<String> permissions;
    public CallbackManager mCallbackManager;
    Context context;
    ProgressDialog pd;
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
   pd=new ProgressDialog(this);

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


                              call_login_webservice(context);





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

    private void call_login_webservice(Context context) {

        pd.show();
        setupSelfSSLCert();

        JsonObject js=new JsonObject();
        js.addProperty("type", "login");
        js.addProperty("vEmail",userLoginEmailEditText.getText().toString().trim());
        js.addProperty("vPassword", userLoginPasswordEditText.getText().toString().trim());



        Log.e("js",js.toString());

        String Webservice_login_url=getResources().getString(R.string.webservice_api_url);
        Log.e("Webservice_login_url",Webservice_login_url);
        Ion.with(context)
                .load(Webservice_login_url)
                .setJsonObjectBody(js)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(result!=null)
                        {
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.enter, R.anim.exit);
                            Log.e("result",result.toString());
                           String login_status=result.get("action").getAsString();
                            if(login_status.contains("1"))
                            {
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.enter, R.anim.exit);
                            }
                            else{
                                showMessage("Incorrect Username or password ");
                            }


if(pd!=null)
{
    pd.dismiss();
}


                           String login_status=result.get("action").getAsString();
                            if(login_status.contains("1"))
                            {
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.enter, R.anim.exit);
                            }
                            else{
                                showMessage("Incorrect Username or password ");
                            }


if(pd!=null)
{
    pd.dismiss();
}


                        }
                        else {
                            Log.e("result_null","result_null");
                        }
                        // do stuff with the result or error
                    }
                });

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




    private static class Trust implements X509TrustManager {

        /**
         * {@inheritDoc}
         */
        @Override
        public void checkClientTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void checkServerTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

    }

    public void setupSelfSSLCert() {
        final LoginActivity.Trust trust = new LoginActivity.Trust();
        final TrustManager[] trustmanagers = new TrustManager[]{trust};
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustmanagers, new SecureRandom());
            Ion.getInstance(context, "rest").getHttpClient().getSSLSocketMiddleware().setTrustManagers(trustmanagers);
            Ion.getInstance(context, "rest").getHttpClient().getSSLSocketMiddleware().setSSLContext(sslContext);
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (final KeyManagementException e) {
            e.printStackTrace();
        }
    }
}
