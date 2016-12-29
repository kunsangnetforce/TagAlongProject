package com.netforceinfotech.tagalong.home.findride.applyfilter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hedgehog.ratingbar.RatingBar;
import com.netforceinfotech.tagalong.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RatingFragment extends Fragment {

    TextView ratingtext;
    public RatingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_rating, container, false);
        RatingBar rb=(RatingBar)v.findViewById(R.id.ratingbar);
         ratingtext=(TextView)v.findViewById(R.id.ratingtext);

        rb.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                ratingtext.setText("("+RatingCount+")");
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

}
