package com.netforceinfotech.tagalong.home.findride.ride_available;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.hedgehog.ratingbar.RatingBar;
import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.chat.driverchat.DriverChatActivity;

import java.util.ArrayList;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int NORMAL = 0;
    private static final int START = 1;
    private final LayoutInflater inflater;
    private Context context;
    ArrayList<MyData> myDatas;

    public MyAdapter(Context context, ArrayList<MyData> myDatas) {
        this.myDatas = myDatas;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    /*  @Override
      public int getItemViewType(int position) {
          if (itemList.get(position).image.isEmpty()) {
              return SIMPLE_TYPE;
          } else {
              return IMAGE_TYPE;
          }
      }
  */


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.row_rides_available, parent, false);
        MyHolder viewHolder = new MyHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ImageView im= (ImageView) holder.itemView.findViewById(R.id.imageViewDp);
        Glide.with(context)
                .load(myDatas.get(position).imageurl)
                .into(im);


        TextView username= (TextView) holder.itemView.findViewById(R.id.user_name);
        username.setText(myDatas.get(position).name);

        RatingBar rating=(RatingBar)holder.itemView.findViewById(R.id.ratingbar) ;
        rating.setStar(myDatas.get(position).starRating);

        TextView departfrom=(TextView) holder.itemView.findViewById(R.id.departfromTextView);
        departfrom.setText(myDatas.get(position).source);

        TextView departto=(TextView) holder.itemView.findViewById(R.id.departtoTextView);
        departto.setText(myDatas.get(position).destination);

        TextView rides_prize=(TextView) holder.itemView.findViewById(R.id.rides_prize);
        rides_prize.setText(myDatas.get(position).price);

        TextView time_textview=(TextView) holder.itemView.findViewById(R.id.time_textview);
        time_textview.setText(myDatas.get(position).departuretime);

        TextView departDateTextView=(TextView) holder.itemView.findViewById(R.id.departDateTextView);
        departDateTextView.setText(myDatas.get(position).departuredate);




        //MyData mydata=new MyData("userid","userimageurl","username","ride_price","sourceaddress","destinationaddress","departuredata","departuretime",2);





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookingSeatsActivity.class);
                context.startActivity(intent);
            }
        });

    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        // return myDatas.size();
        return myDatas.size();
    }
}
