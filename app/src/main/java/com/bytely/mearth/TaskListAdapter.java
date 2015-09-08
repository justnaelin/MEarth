package com.bytely.mearth;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/*
 * Created by juice on 3/14/15.
 */
public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<TaskModel> mActivityList = Collections.emptyList();
    private int levelNum;

    private Communicator communicator;

    public TaskListAdapter(Context context, List<TaskModel> mActivityList) {
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.mActivityList = mActivityList;

        communicator  = (Communicator)context;
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
        holder.mTaskCounter.setText(Integer.toString(current.getTaskCounter()));
       // holder.mTaskLevel.setText(Integer.toString(current.getTaskLevelNum()));
        levelNum = (current.getTaskLevelNum());

    }

    public int getLevelNum() {
        return levelNum;
    }

    @Override
    public int getItemCount() {
        return mActivityList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ViewHolderCommunicator {
        private TextView mTaskLevel;
        private TextView mTaskName;
        private ImageView mTaskIcon;
        private TextView mTaskPointValue; // Point value associated with each activity
        private TextView mAdd;
        private TaskModel mTask;
        private RelativeLayout mRelativeLayout;
        private TextView mTaskCounter; // Keeps track of how many times they've completed a task



        public MyViewHolder(View view) {
            super(view);
            mRelativeLayout = (RelativeLayout) view.findViewById(R.id.card_view_relative_layout);
            mTaskName = (TextView) view.findViewById(R.id.task_name);
            mTaskIcon = (ImageView) view.findViewById(R.id.task_icon);
            mTaskPointValue = (TextView) view.findViewById(R.id.task_point_value);
            mAdd = (TextView) view.findViewById(R.id.add);
            mTaskCounter = (TextView) view.findViewById(R.id.task_counter);
            mTaskCounter.setVisibility(View.GONE);

            mRelativeLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Log.i("ListAdapter", "The level number = " + Integer.toString(levelNum));

            android.support.v4.app.FragmentManager fragmentManager =
                    ((AppCompatActivity) context).getSupportFragmentManager();
            TaskListFragment targetFragment = (TaskListFragment) fragmentManager.findFragmentById(R.id.fragment_container);

            if(levelNum == 1){
                //sending the taskPoints to the confirmPointsDialogFragment

                ConfirmPointsDialogFragment confirmPointsDialogFragment = ConfirmPointsDialogFragment.getInstance(mTask.getTaskPoints(), mTask, mTask.getTaskID());

                confirmPointsDialogFragment.setTargetFragment(targetFragment,TaskListFragment.REQUEST_POINTS);
                confirmPointsDialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "add_points_dialog");
            }

            else{
                targetFragment.addTask(mTask);
                Log.i("The task ID: ", String.valueOf(mTask.getTaskID()));

                ConfirmPictureDialogFragment confirmPictureDialogFragment = ConfirmPictureDialogFragment.getInstance(mTask.getTaskPoints(), mTask, mTask.getTaskID());
                confirmPictureDialogFragment.setTargetFragment(targetFragment, TaskListFragment.REQUEST_POINTS);

                confirmPictureDialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "add_points_dialog");

            }

            //DashboardTasks.getInstance(context).badgeNotification();
            //DashboardTasks.getInstance(context).levelNotification();
        }

        @Override
        public void addTaskToViewHolder(TaskModel taskModel) {
            mTask = taskModel;
        }
    }
}