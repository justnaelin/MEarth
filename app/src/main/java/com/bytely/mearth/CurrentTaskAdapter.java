package com.bytely.mearth;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by juice on 4/25/15.
 */

public class CurrentTaskAdapter extends RecyclerView.Adapter<CurrentTaskAdapter.CurrentTaskViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<TaskModel> mTaskList = Collections.emptyList();
    private Context mContext;
    private static Context sContext;

    public CurrentTaskAdapter(Context context, List<TaskModel> taskModelList) {
        mContext = context.getApplicationContext();
        mLayoutInflater = LayoutInflater.from(context);
        mTaskList = taskModelList;
    }

    @Override
    public CurrentTaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.custom_task_list, parent, false);

        CurrentTaskViewHolder holder = new CurrentTaskViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CurrentTaskViewHolder holder, int position) {
        TaskModel current = mTaskList.get(position);
        holder.mTaskName.setText(current.getTaskName());
        holder.mTaskIcon.setImageBitmap(current.getTaskIcon());
        holder.mTaskCounter.setText(Integer.toString(current.getTaskCounter()));
    }


    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    public class CurrentTaskViewHolder extends RecyclerView.ViewHolder {
        private TextView mTaskName;
        private ImageView mTaskIcon;
        private TextView mAdd;
        private TextView mTaskCounter;
        private int taskId;

        public CurrentTaskViewHolder(View itemView) {
            super(itemView);
            mTaskName = (TextView) itemView.findViewById(R.id.task_name);
            mTaskIcon = (ImageView) itemView.findViewById(R.id.task_icon);
            mAdd = (TextView) itemView.findViewById(R.id.add);
            mTaskCounter = (TextView) itemView.findViewById(R.id.task_counter);
            mAdd.setVisibility(View.GONE);
            mTaskCounter.setVisibility(View.VISIBLE);
        }

    }

}