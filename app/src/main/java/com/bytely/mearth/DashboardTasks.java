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
        mTaskList = new ArrayList<>();
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
            for(int i = 0; i < mTaskList.size(); i++) {
                if(!(task.getTaskID().equals(mTaskList.get(i).getTaskID()))) {
                    mTaskList.add(task);
                    Log.d("addTask", task.getTaskName() + " added");
                }
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

    public void setTaskList(ArrayList<TaskModel> taskList) {
        this.mTaskList = taskList;
    }

}