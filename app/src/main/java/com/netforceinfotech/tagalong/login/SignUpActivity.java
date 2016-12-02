package com.netforceinfotech.tagalong.login;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.login.Validation.Validation;
import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.animations.DroppyFadeInAnimation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private EditText user_name, user_email, user_password, user_repassword;
    private Button signupbutton;
    private Context context;
    private TextView user_prefered_language;
    private ImageView userlangdropdownlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        context = this;

        initView();
        setupToolbar(getString(R.string.signup));

    }

    private void initView() {

        user_name = (EditText) findViewById(R.id.userNameEditText);
        user_email = (EditText) findViewById(R.id.userEmailEditText);
        user_password = (EditText) findViewById(R.id.userPasswordEditText);
        user_repassword = (EditText) findViewById(R.id.userRepasswordEditText);
        user_prefered_language = (TextView) findViewById(R.id.userPreferedLangTextView);
        userlangdropdownlist= (ImageView) findViewById(R.id.langDropDownImageView);
        userlangdropdownlist.setOnClickListener(this);
        signupbutton = (Button) findViewById(R.id.signupButton);
        signupbutton.setOnClickListener(this);

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
    public void onClick(View v) {

        if (v.getId() == R.id.signupButton) {

            check_validation();

        }
        if(v.getId()==R.id.langDropDownImageView){
            showLangOptions();

        }
    }

    private void showLangOptions() {

        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(this,userlangdropdownlist);
        DroppyMenuPopup droppyMenu = droppyBuilder.fromMenu(R.menu.lang_dropdown)
                .triggerOnAnchorClick(false)
                .setOnClick(new DroppyClickCallbackInterface() {
                    @Override
                    public void call(View v, int id) {

                        switch (id){

                            case R.id.dd1:
                                Toast.makeText(getApplicationContext(),"Chat ddl1 clicked",Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd2:
                                Toast.makeText(getApplicationContext(),"Chat ddl2 clicked",Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd3:
                                Toast.makeText(getApplicationContext(),"Chat ddl3 clicked",Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd4:
                                Toast.makeText(getApplicationContext(),"Chat ddl4 clicked",Toast.LENGTH_LONG).show();

                                break;

                        }


                    }
                })
                .setOnDismissCallback(new DroppyMenuPopup.OnDismissCallback() {
                    @Override
                    public void call()
                    {

                        Log.i("Hello","sdfsdaf");
                    }
                })
                .setPopupAnimation(new DroppyFadeInAnimation())
                .setXOffset(5)
                .setYOffset(5)
                .build();
        droppyMenu.show();
    }

    private void check_validation() {


        ConnectivityManager cm = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null) {

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE || activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {


              if(!user_name.getText().toString().isEmpty()){
                  if(isValidName(user_name.getText().toString().trim())){

                      if(!user_email.getText().toString().isEmpty()){
                          if(isValidEmail(user_email.getText().toString().trim())){

                              if(!user_password.getText().toString().isEmpty()){
                                  if(isValidPassword(user_password.getText().toString().trim())){
                                      if(!user_repassword.getText().toString().isEmpty()){


                                          if(user_password.getText().toString().trim().equals(user_repassword.getText().toString().trim())){

                                              showMessage("Validation Successful...");

                                          }else {
                                              user_password.getText().clear();
                                              user_repassword.getText().clear();
                                              user_password.setFocusable(true);
                                              showMessage("Password didn't match");
                                          }

                                      }else {

                                          showMessage("Enter Confirmation Password");
                                      }


                                  }else{
                                      user_password.getText().clear();
                                      user_repassword.getText().clear();
                                      user_password.setFocusable(true);
                                      showMessage("Password should have at least 6 characters");
                                  }


                              }else {

                                  showMessage("Enter Password");
                              }

                          }else {
                              showMessage("Enter a valid Email Adress");
                          }



                      }else {
                          showMessage("Enter Email Address");
                      }

                  }else {
                      showMessage("User Name should have at least 5 characters");
                  }

              } else
              {
                  showMessage("Enter User Name");

              }




            } // end of the second if statement...


        } else {

            // First Else...
            Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

    private boolean isValidName(String name){

        if(name!=null && name.length()>5){
            return true;
        }
        return false;
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

}


