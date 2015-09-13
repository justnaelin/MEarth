package com.bytely.mearth;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by juice on 4/7/15.
 */
public class DashboardTasks extends Activity {
    public static final String POINTS_FILENAME = "total_points";
    public static final String PREFS_KEY = "POINTS_VALUE";
    private static DashboardTasks sDashboardTasks;
    private static Context sContext;
    private static ArrayList<TaskModel> sTaskList;
    private static int sTotalPoints; // User's total points
    private int mBadgeFlag = 0; // Controls badge toasts
    private int mLevelFlag = 0; // Controls level toasts


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

        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = sContext.getSharedPreferences(POINTS_FILENAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putInt(PREFS_KEY, sTotalPoints); //3
        editor.commit(); //4

        Log.d("DashboardTasks", "Added points to user-total");
    }

    public void levelNotification() {
        int duration = Toast.LENGTH_SHORT;
        CharSequence text = "";

        if(sTotalPoints >= 1100 && mLevelFlag == 0)
        {
            text = "You have unlocked Level Two!";
            Toast toast = Toast.makeText(sContext.getApplicationContext(), text, duration);
            toast.show();
            mLevelFlag++;
        }
        if(sTotalPoints >= 4400)
        {
            text = "You have unlocked Level Three!";
            Toast toast = Toast.makeText(sContext.getApplicationContext(), text, duration);
            mLevelFlag++;
            if(mLevelFlag == 3)
                toast.show();
        }
    }

    public int getPoints() {
        SharedPreferences settings;
        int pointsValue;
        settings = sContext.getSharedPreferences(POINTS_FILENAME, Context.MODE_PRIVATE); //1
        pointsValue = settings.getInt(PREFS_KEY, 0); //2
        sTotalPoints = pointsValue;
        return sTotalPoints;
    }

    public void setTaskList(ArrayList<TaskModel> taskList) {
        this.sTaskList = taskList;
    }

}