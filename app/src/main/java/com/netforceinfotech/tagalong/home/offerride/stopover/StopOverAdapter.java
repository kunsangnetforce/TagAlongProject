package com.netforceinfotech.tagalong.home.offerride.stopover;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.tagalong.R;

import java.util.ArrayList;

/**
 * Created by JitendraSingh on 12/8/2016.
 */

public class StopOverAdapter extends RecyclerView.Adapter<StopOverViewHolder> {

    ArrayList<StopOverData> stopOverDatas;
    Context context;

    public StopOverAdapter(Context context,ArrayList<StopOverData> stopOverDatas){

        this.context=context;
        this.stopOverDatas=stopOverDatas;
    }

    @Override
    public StopOverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_stop_over_list, parent, false);
        return new StopOverViewHolder(itemView,this);

    }

    @Override
    public void onBindViewHolder(StopOverViewHolder holder, int position) {


          holder.edittextStopOver.setText(stopOverDatas.get(position).getPlace());


    }

    @Override
    public int getItemCount() {

        return stopOverDatas.size();


    }
    public void removeItem(int position) {
        stopOverDatas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,stopOverDatas.size());

        // Add whatever you want to do when removing an Item
    }

}
