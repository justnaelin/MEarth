package com.bytely.mearth;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * UNDER CONSTRUCTION
 * Created by naelinaquino on 7/6/15.
 */
public class BadgeAdapter extends RecyclerView.Adapter<BadgeAdapter.BadgeViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<BadgeModel> mBadgeList = Collections.emptyList();


    private Communicator communicator;

    public BadgeAdapter(Context context, List<BadgeModel> mBadgeList) {
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.mBadgeList = mBadgeList;


        communicator  = (Communicator)context;
    }

    @Override
    public BadgeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.custom_badge, parent, false);
        BadgeViewHolder holder = new BadgeViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(BadgeViewHolder holder, int position) {
        BadgeModel current = mBadgeList.get(position);
        //holderCommunicator.addTaskToViewHolder(current);
        holder.mBadgeName.setText(current.getBadgeName());

        // Communicate with singleton class (DashboardTasks) to access TaskModel array
        ArrayList<TaskModel> mTaskList = new ArrayList<TaskModel>();
        mTaskList = DashboardTasks.getInstance(context).getTaskList();
        int numTimesCompleted = mTaskList.get(position).getTaskCounter();
        //
        int taskId = mTaskList.get(position).getTaskID();

        // TESTING PURPOSE
        /*if(!current.getIsComplete()) { // TODO: Set is completed according to numTimesCompleted
            Bitmap grayBadge = FormatIcon.toGrayscale(current.getBadgeIcon());
            holder.mBadgeIcon.setImageBitmap(grayBadge);
        }
        else */
        holder.mBadgeIcon.setImageBitmap(current.getBadgeIcon());
    }

    @Override
    public int getItemCount() { return 31; }

    public class BadgeViewHolder extends RecyclerView.ViewHolder {
        private TextView mBadgeName;
        private ImageView mBadgeIcon;
        private BadgeModel mBadge;
        private RelativeLayout mRelativeLayout;

        public BadgeViewHolder(View view) {
            super(view);
            mRelativeLayout = (RelativeLayout) view.findViewById(R.id.badge_view_relative_layout);
            mBadgeName = (TextView) view.findViewById(R.id.badge_name);
            mBadgeIcon = (ImageView) view.findViewById(R.id.badge_icon);

        }


    }

}
