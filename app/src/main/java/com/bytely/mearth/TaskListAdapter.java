package com.bytely.mearth;

import android.content.Context;
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
 * Created by juice on 3/14/15.
 */
public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<TaskModel> mActivityList = Collections.emptyList();
    private TaskAdapterCommunicator mTaskAdapterCommunicator;

    public TaskListAdapter(Context context, List<TaskModel> mActivityList) {
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.mActivityList = mActivityList;
        mTaskAdapterCommunicator = (TaskAdapterCommunicator) context;
        //this.mBitmapData = mBitmapData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.custom_task_list, parent, false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final TaskModel current = mActivityList.get(position);
        holder.mTaskName.setText(current.getTaskName());
        holder.mTaskIcon.setImageBitmap(current.getTaskIcon());
        holder.mTaskName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Click", "Clicked: " + current.getTaskName());
                //DashboardTasks.getInstance(context).addTask(current);
                mTaskAdapterCommunicator.addTask(current);
            }
        });
        holder.mTaskIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLick", "Clicked" + current.getTaskName());
                //DashboardTasks.getInstance(context).addTask(current);
                mTaskAdapterCommunicator.addTask(current);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mActivityList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTaskName;
        private ImageView mTaskIcon;


        public MyViewHolder(View view) {
            super(view);
            mTaskName = (TextView) view.findViewById(R.id.task_name);
            mTaskIcon = (ImageView) view.findViewById(R.id.task_icon);
        }
    }

}