package com.bytely.mearth;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by juice on 4/7/15.
 */
public class DashboardTasks {
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
        Log.d("DashboardTasks", "Added points to user-total");

    }

    public void badgeNotification() {
        // NOTIFY WHEN BADGE IS EARNED
        CharSequence text = "You have earned a badge!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(sContext.getApplicationContext(), text, duration);

        if(sTotalPoints >= 2100 && mBadgeFlag == 0)
        {
            toast.show();
            mBadgeFlag++;
        }
        if(sTotalPoints >= 4200)
        {
            mBadgeFlag++;
            if(mBadgeFlag == 3) {
                toast.show();
                mBadgeFlag++;
            }
        }
        if(sTotalPoints >= 6300)
        {
            mBadgeFlag++;
            if(mBadgeFlag == 5) {
                toast.show();
                mBadgeFlag++;
            }
        }
        if(sTotalPoints >= 8400 && mBadgeFlag == 3)
        {
            mBadgeFlag++;
            if(mBadgeFlag == 7) {
                toast.show();
                mBadgeFlag++;
            }
        }
        if(sTotalPoints >= 10500 && mBadgeFlag == 4)
        {
            mBadgeFlag++;
            if(mBadgeFlag == 9) {
                toast.show();
                mBadgeFlag++;
            }
        }
        if(sTotalPoints >= 12600 && mBadgeFlag == 5)
        {
            mBadgeFlag++;
            if(mBadgeFlag == 11) {
                toast.show();
                mBadgeFlag++;
            }
        }
        if(sTotalPoints >= 14700 && mBadgeFlag == 6)
        {
            mBadgeFlag++;
            if(mBadgeFlag == 13) {
                toast.show();
                mBadgeFlag++;
            }
        }
        if(sTotalPoints >= 16800 && mBadgeFlag == 7)
        {
            mBadgeFlag++;
            if(mBadgeFlag == 15) {
                toast.show();
                mBadgeFlag++;
            }
        }
        if(sTotalPoints >= 31800 && mBadgeFlag == 8)
        {
            mBadgeFlag++;
            if(mBadgeFlag == 17) {
                toast.show();
                mBadgeFlag++;
            }
        }
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


    public int getPoints() {return sTotalPoints;}

    public void setTaskList(ArrayList<TaskModel> taskList) {
        this.sTaskList = taskList;
    }

}