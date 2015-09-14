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
    public static final String B_FLAG_KEY = "BADGE_FLAG_VALUE";
    public static final String L_FLAG_KEY = "LEVEL_FLAG_VALUE";
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

        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = sContext.getSharedPreferences("level_flag", Context.MODE_PRIVATE);
        mLevelFlag = settings.getInt(L_FLAG_KEY, 0);

        if(sTotalPoints >= 1100 && mLevelFlag == 0)
        {
            text = "You have unlocked Level Two!";
            Toast toast = Toast.makeText(sContext.getApplicationContext(), text, duration);
            toast.show();
            mLevelFlag++;
        }
        if(sTotalPoints >= 4100)
        {
            text = "You have unlocked Level Three!";
            Toast toast = Toast.makeText(sContext.getApplicationContext(), text, duration);
            mLevelFlag++;
            if(mLevelFlag == 2)
                toast.show();
            mLevelFlag++;
        }
        editor = settings.edit();

        editor.putInt(L_FLAG_KEY, mLevelFlag);
        editor.commit();
    }

    public void badgeNotification() {
        int duration = Toast.LENGTH_SHORT;
        CharSequence text = "You have earned a badge";
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = sContext.getSharedPreferences("badge_flag", Context.MODE_PRIVATE);
        mBadgeFlag = settings.getInt(B_FLAG_KEY, 0);

        Toast mBadgeToast = Toast.makeText(sContext.getApplicationContext(), text, duration);

        int userPoints = getPoints();

        if(userPoints >= 25000 && mBadgeFlag == 8) {
            mBadgeToast.show();
            mBadgeFlag++;
        }
        else if(userPoints >= 18000 && mBadgeFlag == 7) {
            mBadgeToast.show();
            mBadgeFlag++;
        }
        else if(userPoints >= 15000 && mBadgeFlag == 6) {
            mBadgeToast.show();
            mBadgeFlag++;
        }
        else if(userPoints >= 12000 && mBadgeFlag == 5) {
            mBadgeToast.show();
            mBadgeFlag++;
        }
        else if(userPoints >= 8000 && mBadgeFlag == 4) {
            mBadgeToast.show();
            mBadgeFlag++;
        }
        else if(userPoints >= 6000 && mBadgeFlag == 3) {
            mBadgeToast.show();
            mBadgeFlag++;
        }
        else if(userPoints >= 4000 && mBadgeFlag == 2) {
            mBadgeToast.show();
            mBadgeFlag++;
        }
        else if(userPoints >= 2000 && mBadgeFlag == 1) {
            mBadgeToast.show();
            mBadgeFlag++;
        }
        else if(userPoints >= 1000 && mBadgeFlag == 0)
        {
            mBadgeToast.show();
            mBadgeFlag++;
        }

        editor = settings.edit();

        editor.putInt(B_FLAG_KEY, mBadgeFlag);
        editor.commit();
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