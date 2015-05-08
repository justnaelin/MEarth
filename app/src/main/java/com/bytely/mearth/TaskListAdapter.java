package com.bytely.mearth;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    private Communicator communicator;

    public TaskListAdapter(Context context, List<TaskModel> mActivityList) {
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.mActivityList = mActivityList;
        communicator  = (Communicator) context;
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
        TaskModel current = mActivityList.get(position);
        ViewHolderCommunicator holderCommunicator = holder;
        holderCommunicator.addTaskToViewHolder(current);
        holder.mTaskName.setText(current.getTaskName());
        holder.mTaskIcon.setImageBitmap(current.getTaskIcon());
        holder.mTaskPointValue.setText(Integer.toString(current.getTaskPoints()));
    }

    @Override
    public int getItemCount() {
        return mActivityList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ViewHolderCommunicator {
        private TextView mTaskName;
        private ImageView mTaskIcon;
        private Button mAddButton;
        private TaskModel mTask;
        private TextView mTaskPointValue;

        public MyViewHolder(View view) {
            super(view);
            mTaskName = (TextView) view.findViewById(R.id.task_name);
            mTaskIcon = (ImageView) view.findViewById(R.id.task_icon);
            mTaskPointValue = (TextView) view.findViewById(R.id.task_point_value);
            mAddButton = (Button) view.findViewById(R.id.add_button);
            mAddButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    DashboardTasks.getInstance(context).addPoints(mTask.getTaskPoints()); // Gets the point-value associated with
                                                                                            // card that was clicked ("+")
                }
            });

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            DashboardTasks.getInstance(context).addTask(mTask);
            Log.d("MyViewHolder", mTask.getTaskName() + " added");
        }

        @Override
        public void addTaskToViewHolder(TaskModel taskModel) {
            mTask = taskModel;
        }
    }

}