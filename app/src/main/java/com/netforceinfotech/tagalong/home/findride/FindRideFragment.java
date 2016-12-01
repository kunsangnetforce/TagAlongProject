package com.netforceinfotech.tagalong.home.findride;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.home.findride.ride_available.RidesActivity;
import com.netforceinfotech.tagalong.home.offerride.RideDetailActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindRideFragment extends Fragment implements View.OnClickListener,DatePickerDialog.OnDateSetListener{


    ImageView imageFindRide;
    Context context;
    private EditText selectDateEditText;


    public FindRideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find_ride, container, false);
        context=getActivity();
        initView(view);

        return view;
    }

    private void initView(View view) {
        imageFindRide = (ImageView) view.findViewById(R.id.imageFindRide);
        Glide.with(context)
                .fromResource()
                .asBitmap()
                .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                .load(R.drawable.image).into(imageFindRide);
        view.findViewById(R.id.buttonSearch).setOnClickListener(this);

        selectDateEditText = (EditText) view.findViewById(R.id.selectDateEditText);
        selectDateEditText.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonSearch:
                Intent intent=new Intent(context,RidesActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);

                break;
            case R.id.selectDateEditText:
                showSelectDatePopup();
        }
    }

    private void showSelectDatePopup() {

        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                FindRideFragment.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
    }

    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

            Date date2 = new Date();
            SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MMM-dd");
            try {
                date2 = date_format.parse(year + "-" + monthOfYear + "-" + dayOfMonth);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            SimpleDateFormat outDate = new SimpleDateFormat("dd - MM - yyyy");

            selectDateEditText.setText(outDate.format(date2));

    }
}
