package com.netforceinfotech.tagalong.home.findride.applyfilter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.tagalong.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarComfortFragment extends Fragment {


    public CarComfortFragment() {
        // Required empty public constructor

//        Lasso tashi
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_comfort, container, false);
    }

}
