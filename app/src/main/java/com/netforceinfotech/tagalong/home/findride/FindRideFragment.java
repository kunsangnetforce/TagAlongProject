package com.netforceinfotech.tagalong.home.findride;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.netforceinfotech.tagalong.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindRideFragment extends Fragment implements View.OnClickListener {


    ImageView imageFindRide;
    Context context;

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


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonSearch:
                Intent intent=new Intent(context,CantFindRideActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);

                break;
        }
    }
}
