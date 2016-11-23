package com.netforceinfotech.tagalong.myprofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.netforceinfotech.tagalong.R;

public class MyProfileActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageViewEmail, imageViewDob, imageViewContact, imageViewAddress, imageViewLanguage, imageViewDescription;
    EditText etEmail, etDob, etContact, etAddress, etLanguage, etDescription;
    Switch mySwitch;
    LinearLayout linearLayoutEditProfile;
    TextView textViewSave;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        initView();
        setupToolbar(getString(R.string.my_profile));
    }

    private void initView() {
        textViewSave = (TextView) findViewById(R.id.textViewSave);
        textViewSave.setOnClickListener(this);
        linearLayoutEditProfile = (LinearLayout) findViewById(R.id.linearLayoutEditProfile);
        findViewById(R.id.linearLayoutEditProfile).setOnClickListener(this);
        imageViewAddress = (ImageView) findViewById(R.id.imageViewAddress);
        imageViewEmail = (ImageView) findViewById(R.id.imageViewEmail);
        imageViewDob = (ImageView) findViewById(R.id.imageViewDob);
        imageViewContact = (ImageView) findViewById(R.id.imageViewContact);
        imageViewLanguage = (ImageView) findViewById(R.id.imageViewLanguage);
        imageViewDescription = (ImageView) findViewById(R.id.imageViewDescription);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etDob = (EditText) findViewById(R.id.etDob);
        etContact = (EditText) findViewById(R.id.etContact);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etLanguage = (EditText) findViewById(R.id.etLanguage);
        etDescription = (EditText) findViewById(R.id.etDescription);
        mySwitch = (Switch) findViewById(R.id.mySwitch);
        mySwitch.setChecked(false);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setEditable(b);
            }
        });
        setEditable(false);
    }

    private void setEditable(boolean b) {
        if (b) {
            imageViewAddress.setVisibility(View.VISIBLE);
            imageViewEmail.setVisibility(View.VISIBLE);
            imageViewDob.setVisibility(View.VISIBLE);
            imageViewContact.setVisibility(View.VISIBLE);
            imageViewLanguage.setVisibility(View.VISIBLE);
            imageViewDescription.setVisibility(View.VISIBLE);
            etEmail.setEnabled(true);
            etDob.setEnabled(true);
            etContact.setEnabled(true);
            etAddress.setEnabled(true);
            etLanguage.setEnabled(true);
            etDescription.setEnabled(true);
            linearLayoutEditProfile.setVisibility(View.GONE);
            textViewSave.setVisibility(View.VISIBLE);
        } else {
            imageViewAddress.setVisibility(View.GONE);
            imageViewEmail.setVisibility(View.GONE);
            imageViewDob.setVisibility(View.GONE);
            imageViewContact.setVisibility(View.GONE);
            imageViewLanguage.setVisibility(View.GONE);
            imageViewDescription.setVisibility(View.GONE);
            etEmail.setEnabled(false);
            etDob.setEnabled(false);
            etContact.setEnabled(false);
            etAddress.setEnabled(false);
            etLanguage.setEnabled(false);
            etDescription.setEnabled(false);
            linearLayoutEditProfile.setVisibility(View.VISIBLE);
            textViewSave.setVisibility(View.GONE);

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearLayoutEditProfile:
                mySwitch.performClick();
                break;
            case R.id.textViewSave:
                mySwitch.performClick();
                break;
        }
    }

    private void setupToolbar(String s) {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(s);

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
}
