package com.bytely.mearth;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by juice on 4/7/15.
 */
public class DashboardTasks {
    private static DashboardTasks sDashboardTasks;
    private static Context sContext;
    private static ArrayList<TaskModel> sTaskList;
    private static int sTotalPoints; // User's total points

    private DashboardTasks(Context context) {
        sContext = context;
        sTaskList = new ArrayList<>();
        sTotalPoints = 0; // Initialize user's points to 0
    }

    public static DashboardTasks getInstance(Context context) {
        if(sDashboardTasks == null) {
            sDashboardTasks = new DashboardTasks(context.getApplicationContext());
        }
        return sDashboardTasks;
    }

    public ArrayList<TaskModel> getTaskList() {
        return sTaskList;
    }

    public void addTask(TaskModel task) {
        if(sTaskList.size() == 0) {
            sTaskList.add(task);
        } else {
            if(sTaskList.contains(task)) {
                return;
            } else {
                sTaskList.add(task);
            }
        }
    }

    public void removeTask(TaskModel task) {
        for(TaskModel taskInList : sTaskList) {
            if(task.getTaskID() == taskInList.getTaskID()) {
                sTaskList.remove(task);
            }
        }
    }

    // Adds points to user's overall total points
    public void addPoints(int pointsToBeAdded) {
        sTotalPoints += pointsToBeAdded;
        Log.d("DashboardTasks", "Added points to user-total");

    }


    public int getPoints() {return sTotalPoints;}

    public void setTaskList(ArrayList<TaskModel> taskList) {
        this.sTaskList = taskList;
    }

}