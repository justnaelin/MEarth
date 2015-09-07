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

    /**
     * This method is called when RecyclerView needs a new {@link MyViewHolder} of the given type to represent an item.
     * This method creates a View object by inflating our custom view that is defined in
     * layout/custom_task_list.xml using the {@link #mLayoutInflater} member. The view object that is created
     * is passed in to the constructor of {@link MyViewHolder} when creating a MyViewHolder object.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A MyViewHolder object that contains the inflated widgets in layout/list_image.xml
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.custom_task_list, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    /**
     * Called by RecyclerView to display the data at the specified position. We obtain the object
     * that contains the data to be displayed by accessing the element in {@link #mActivityList} at
     * index position.
     *
     * @param holder The {@link MyViewHolder} which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
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
    /**
     * Returns the total number of items in our data set {@link #mActivityList}
     *
     * @return The size of our data set {@link #mActivityList}
     */

    public int getLevelNum() {
        return levelNum;
    }

    @Override
    public int getItemCount() {
        return mActivityList.size();
    }

    /**
     * This class describes an item view and creates a Java reference to the widgets of
     * our custom view. An object of this class is created by the RecyclerView's onCreateViewHolder
     * method @see #onCreateViewHolder(ViewGroup parent, int viewType)
     */
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
                ConfirmPointsDialogFragment confirmPointsDialogFragment = ConfirmPointsDialogFragment.getInstance(mTask.getTaskPoints(), mTask);

                confirmPointsDialogFragment.setTargetFragment(targetFragment,TaskListFragment.REQUEST_POINTS);
                confirmPointsDialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "add_points_dialog");
            }

            else{
                targetFragment.addTask(mTask);
                ConfirmPictureDialogFragment confirmPictureDialogFragment = ConfirmPictureDialogFragment.getInstance(mTask.getTaskPoints(), mTask);
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