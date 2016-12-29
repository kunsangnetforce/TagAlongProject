package com.netforceinfotech.tagalong.home.findride.applyfilter;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appyvet.rangebar.RangeBar;
import com.netforceinfotech.tagalong.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimeFragment extends Fragment implements RangeBar.OnRangeBarChangeListener{

    private RangeBar rangeBar;
    Context context;


    public TimeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_time,container,false);

        // Inflate the layout for this fragment

        rangeBar = (RangeBar) view.findViewById(R.id.rangeBar);
        rangeBar.setOnRangeBarChangeListener(this);
        return view;
    }

    @Override
    public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {


Log.e("leftPinIndex",leftPinIndex+"*****"+rightPinIndex);
    }
}
