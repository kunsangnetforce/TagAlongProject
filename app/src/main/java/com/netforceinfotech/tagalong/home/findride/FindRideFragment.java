package com.netforceinfotech.tagalong.home.findride;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.home.findride.ride_available.RidesActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindRideFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {


    String TAG = "google_result";
    private static final int PLACE_FROM = 101;
    private static final int PLACE_TO = 105;
    ImageView imageFindRide;
    Context context;
    private EditText selectDateEditText, editTextFrom ,editTextTo;


    public FindRideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find_ride, container, false);
        context = getActivity();
        initView(view);

        return view;
    }

    private void initView(View view) {
        editTextTo= (EditText) view.findViewById(R.id.et_to);
        editTextTo.setOnClickListener(this);

        editTextFrom = (EditText) view.findViewById(R.id.et_from);
        editTextFrom.setOnClickListener(this);
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
        switch (view.getId()) {
            case R.id.et_to:
                try{
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(getActivity());
                    startActivityForResult(intent, PLACE_TO);

                }catch(GooglePlayServicesRepairableException e){

                    Log.d("Error...", String.valueOf(e));
                }

                catch (GooglePlayServicesNotAvailableException e){}
                break;

            case R.id.et_from:

                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(getActivity());
                    startActivityForResult(intent, PLACE_FROM);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                    Log.d("Errorrr", String.valueOf(e));
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
                break;
            case R.id.buttonSearch:

                Intent intent = new Intent(context, RidesActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);

                break;
            case R.id.selectDateEditText:
                showSelectDatePopup();
                break;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_FROM) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getActivity(), data);
                String plaze= place.getName().toString();
                Log.i(TAG, "Place: " + place.getName());
                showMessage("The Place name is"+place.getName());
                editTextFrom.setText(plaze);

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                // TODO: Handle the error.
                Log.i(TAG, status.getStatusMessage());
                Log.i(TAG, status.toString());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        } // ends here...

        if(requestCode==PLACE_TO){

            if(resultCode==RESULT_OK){
                Place place=PlaceAutocomplete.getPlace(getActivity(),data);
                String plaze= place.getName().toString();
                editTextTo.setText(plaze);
            }else if(resultCode==PlaceAutocomplete.RESULT_ERROR){
                Status status = PlaceAutocomplete.getStatus(getActivity(),data);
                Log.d("StatusInfo","StatusMessage="+status.getStatusMessage()+"Status="+status.getStatus());
            }
        }


    }

    private void showMessage(String s) {
        Toast.makeText(context,s, Toast.LENGTH_SHORT).show();
    }
}
