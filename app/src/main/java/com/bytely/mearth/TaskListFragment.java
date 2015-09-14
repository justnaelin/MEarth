package com.bytely.mearth;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskListFragment extends Fragment {
    /*
    private ImageView mActivityOneImage;
    private TextView mActivityOneTitle;
    private ImageView mActivityTwoImage;
    private TextView mActivityTwoTitle;
    private ImageView mActivityThreeImage;
    private TextView mActivityThreeTitle;
    private ImageView mActivityFourImage;
    private TextView mActivityFourTitle;
    */

    private TaskModel[] mTaskList;
    private TaskListAdapter mTaskListAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Communicator comm;
    private int fragmentId;
    public static final int REQUEST_POINTS = 0;
    private TextView mUserPoints;
    TaskModel task;



    public TaskListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comm = (Communicator) getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        fragmentId = getArguments().getInt("fragment_id");

        Log.d("TaskList fragmentid", String.valueOf(fragmentId));
        mTaskList = comm.getTaskArray(fragmentId);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.task_list);
        List<TaskModel> taskModelList = new ArrayList<> (Arrays.asList(mTaskList));

        mTaskListAdapter = new TaskListAdapter(getActivity(), taskModelList);
        mRecyclerView.setAdapter(mTaskListAdapter);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Displays user's total points in profile fragment
        mUserPoints = (TextView) view.findViewById(R.id.user_points);
        mUserPoints.setText(Integer.toString((DashboardTasks.getInstance(getActivity()).getPoints())));

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        comm = (Communicator) getActivity();
        comm.updateActionBar();

    }

    public static TaskListFragment getInstance(int fragmentId) {
        TaskListFragment taskListFragment = new TaskListFragment();

        Bundle args = new Bundle();
        args.putInt("fragment_id", fragmentId);
        taskListFragment.setArguments(args);
        return taskListFragment;
    }

    public void displayUsersPoints() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mUserPoints.setText(Integer.toString((DashboardTasks.getInstance(getActivity()).getPoints())));
        Log.d("TaskList", "Inside onActivityResult");
    }

    public void printMessage() {
        Log.d("TaskListFragment", "Inside TaskListFragment");
    }

    public void addTask(TaskModel taskModel) {
        Log.d("TaskListsFragment", "Inside the add'task Method");

        task = taskModel;

        return;
    }

    //adds task points for level two and three
    public void addPoints() {

        Log.d("TaskListFragment", "Points" + task.getTaskPoints());
        DashboardTasks.getInstance(getActivity()).addPoints(task.getTaskPoints());
        mUserPoints.setText(Integer.toString((DashboardTasks.getInstance(getActivity()).getPoints())));
        DashboardTasks.getInstance(getActivity()).addTask(task, task.getTaskID());
        Log.d("TaskLisFragment", "Inside addPoints method finish  adding points");


    }



}
