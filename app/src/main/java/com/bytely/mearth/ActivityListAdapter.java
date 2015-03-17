package com.bytely.mearth;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by juice on 3/14/15.
 */
public class ActivityListAdapter extends RecyclerView.Adapter<ActivityListAdapter.MyViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<ActivityModel> mActivityList = Collections.emptyList();

    public ActivityListAdapter(Context context, List<ActivityModel> mActivityList) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mActivityList = mActivityList;
        //this.mBitmapData = mBitmapData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.custom_activity_list, parent, false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ActivityModel current = mActivityList.get(position);
        holder.mActivityName.setText(current.getActivityName());
        holder.mActivityIcon.setImageBitmap(current.getActivityIcon());
    }

    @Override
    public int getItemCount() {
        return mActivityList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView mActivityName;
        private ImageView mActivityIcon;


        public MyViewHolder(View view) {
            super(view);
            mActivityName = (TextView) view.findViewById(R.id.activity_name);
            mActivityIcon = (ImageView) view.findViewById(R.id.activity_icon);
        }
    }
}
