package com.bytely.mearth;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by juice on 4/7/15.
 */
public class DashboardTasks implements Parcelable {
    private static DashboardTasks mDashboardTasks;
    private static Context mContext;
    private static ArrayList<TaskModel> mTaskList;
    private static int mTotalPoints; // User's total points

    private DashboardTasks(Context context) {
        mContext = context;
        mTaskList = new ArrayList<>();
        mTotalPoints = 0; // Initialize user's points to 0
    }

    public static DashboardTasks getInstance(Context context) {
        if(mDashboardTasks == null) {
            Log.d("Singleton", "New Singleton Instance");
            mDashboardTasks = new DashboardTasks(context.getApplicationContext());
        }
        return mDashboardTasks;
    }

    public ArrayList<TaskModel> getTaskList() {
        return mTaskList;
    }

    public void addTask(TaskModel task) {
        if(mTaskList.size() == 0) {
            mTaskList.add(task);
            Log.d("DashSingleton", task.getTaskName() + " added");
        } else {
            for(TaskModel taskInList : mDashboardTasks.mTaskList) {
                if(!(task.getTaskID().equals(taskInList.getTaskID()))) {
                    mTaskList.add(task);
                    Log.d("addTask", task.getTaskName() + " added");
                }
            }
        }
    }

    public void removeTask(TaskModel task) {
        for(TaskModel taskInList : mDashboardTasks.mTaskList) {
            if(task.getTaskID() == taskInList.getTaskID()) {
                mTaskList.remove(task);
            }
        }
    }

    // Adds points to user's overall total points
    public void addPoints(int pointsToBeAdded) {
        mTotalPoints += pointsToBeAdded;
        Log.d("DashboardTasks", "Added points to user-total");

    }

    public int getPoints() {return mTotalPoints;}

    public void setTaskList(ArrayList<TaskModel> taskList) {
        this.mTaskList = taskList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}