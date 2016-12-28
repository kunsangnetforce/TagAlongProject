package com.netforceinfotech.tagalong.home.findride;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.home.HomeActivity;
import com.netforceinfotech.tagalong.home.findride.ride_available.MyData;
import com.netforceinfotech.tagalong.home.findride.ride_available.RidesActivity;
import com.netforceinfotech.tagalong.login.LoginActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
    ProgressDialog pd;
    String  place_From_Address,place_To_Address,place_To_lat,place_To_long,place_From_lat,place_From_long,From_lat_long;

public static ArrayList<MyData> mydata;

    private EditText selectDateEditText, editTextFrom ,editTextTo;


    public FindRideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find_ride, container, false);
        mydata=new ArrayList<MyData>();
        pd=new ProgressDialog(getActivity());
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

                if(editTextFrom.getText().length()!=0)

                {
                    if(editTextTo.getText().length()!=0)
                    {
                        if(selectDateEditText.getText().length()!=0)
                        {
                            call_find_ride_webservice(getActivity());





                        }
                    }

                    else
                    {
                        Toast.makeText(getActivity(),"Please Enter To address",Toast.LENGTH_SHORT).show();



                    }

                }
                else{
                Toast.makeText(getActivity(),"Please Enter From address",Toast.LENGTH_SHORT).show();
            }

                break;
            case R.id.selectDateEditText:
                showSelectDatePopup();
                break;
        }
    }

    private void call_find_ride_webservice(FragmentActivity activity) {






        pd.show();
        setupSelfSSLCert();

        JsonObject js=new JsonObject();
        js.addProperty("type", "find_ride");
        js.addProperty("searchdate",selectDateEditText.getText().toString().trim());
        js.addProperty("From_lat_long",place_From_lat );
        js.addProperty("To_lat_long",place_To_lat );

        js.addProperty("To",editTextTo.getText().toString().trim());
        js.addProperty("From",editTextFrom.getText().toString().trim());


        Log.e("js_login",js.toString());


        String find_ride_webservice="https://tag-along.net/webservice.php";
        Log.e("find_ride_webservice",find_ride_webservice);
        Ion.with(context)
                .load(find_ride_webservice)
                .setJsonObjectBody(js)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(result!=null)
                        {
JsonArray mainarray=result.getAsJsonArray("mainarr");
                            if(mainarray.size()!=0) {
                                for (int i = 0; i < mainarray.size(); i++) {
                                    // "userid","userimageurl","username","ride_price","sourceaddress","destinationaddress","departuredata","departuretime",2

                                    JsonObject js = mainarray.get(i).getAsJsonObject();
                                    String Memberid = js.get("iMemberId").getAsString();
                                    String userimageurl = js.get("image").getAsString();
                                    String username = js.get("FirstName").getAsString();
                                    String ride_price = js.get("price").getAsString();
                                    String sourceaddress = js.get("from").getAsString();
                                    String destinationaddress = js.get("to").getAsString();
                                    String departuredate = js.get("start_date").getAsString();
                                    String departuretime = js.get("start_time").getAsString();
                                    Float rating = Float.valueOf(js.get("rating").getAsString());

                                    Log.e("result_ride_webservice", Memberid + userimageurl + username + ride_price + sourceaddress + destinationaddress + departuredate +
                                            departuretime + rating);
                                    mydata.add(new MyData(Memberid, userimageurl, username, ride_price, sourceaddress, destinationaddress, departuredate, departuretime, rating));
                                }

                                Intent intent = new Intent(context, RidesActivity.class);
                                startActivity(intent);
                                getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);

                            }
//                            String login_status=result.get("action").getAsString();
//                            if(login_status.contains("1"))
//                            {
//
//                                Intent intent = new Intent(context, RidesActivity.class);
//                                startActivity(intent);
//                                getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
//                            }
//                            else{
//                                showMessage("result null ");
//                            }
                            Log.e("result_ride_webservice",result.toString());


                            if(pd!=null)
                            {
                                pd.dismiss();
                            }


                        }
                        else {
                            Intent intent = new Intent(context, RidesActivity.class);
                            startActivity(intent);
                            getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
                            Log.e("result_null","result_null");
                        }
                        // do stuff with the result or error
                    }
                });













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

        String month=String.valueOf(monthOfYear+1);

        selectDateEditText.setText(year + "-" + month + "-" + dayOfMonth);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_FROM) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getActivity(), data);
                String plaze= place.getName().toString();
                Log.e(TAG, "Place: " + place.getName()+place.getLatLng().latitude+","+place.getName()+place.getLatLng().longitude+place.getAddress());
               // showMessage("The Place name is"+place.getName());

                From_lat_long=String.valueOf(place.getLatLng().latitude)+","+String.valueOf(place.getLatLng().longitude);;




                place_From_Address=place.getAddress().toString().trim();

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
                place_To_Address=place.getAddress().toString().trim();

                        From_lat_long=String.valueOf(place.getLatLng().latitude)+","+String.valueOf(place.getLatLng().longitude);

            }else if(resultCode==PlaceAutocomplete.RESULT_ERROR){
                Status status = PlaceAutocomplete.getStatus(getActivity(),data);
                Log.d("StatusInfo","StatusMessage="+status.getStatusMessage()+"Status="+status.getStatus());
            }
        }


    }

    private void showMessage(String s) {
        Toast.makeText(context,s, Toast.LENGTH_SHORT).show();
    }



    public static class Trust implements X509TrustManager {

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







