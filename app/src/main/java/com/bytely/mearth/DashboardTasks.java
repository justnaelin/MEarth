package com.bytely.mearth;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by juice on 4/7/15.
 */
public class DashboardTasks {
    private static DashboardTasks mDashboardTasks;
    private static Context mContext;
    private static ArrayList<TaskModel> mTaskList;

    private DashboardTasks(Context context) {
        mContext = context;
        mTaskList = new ArrayList<TaskModel>();
    }

    public static DashboardTasks getInstance(Context context) {
        if(mDashboardTasks == null) {
            mDashboardTasks = new DashboardTasks(context.getApplicationContext());
        }

        return mDashboardTasks;
    }

    public ArrayList<TaskModel> getTaskList() {
        return mTaskList;
    }

    public void addTask(TaskModel task) {
        for(TaskModel taskInList : mTaskList) {
            if(!(task.getTaskID().equals(taskInList.getTaskID()))) {
                mTaskList.add(task);
                Log.d("addTask", task.getTaskName() + " added");
            }
        }
    }

    public void removeTask(TaskModel task) {
        for(TaskModel taskInList : mTaskList) {
            if(task.getTaskID() == taskInList.getTaskID()) {
                mTaskList.remove(task);
            }
        }
    }
}