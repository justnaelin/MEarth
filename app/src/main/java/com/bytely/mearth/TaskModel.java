package com.bytely.mearth;

import android.graphics.Bitmap;


public class TaskModel {
    private String mTaskName;
    private int mTaskDescription;
    private Bitmap mTaskIcon;
    private int mTaskPoints;
    private boolean mIsCompleted;
<<<<<<< HEAD
    private  int mTaskLevelNum;
    private UUID mTaskID;
    private int mTaskCounter;

    public TaskModel(String taskName, int taskDescription, Bitmap taskIcon, int taskPoints, int taskCounter, int levelNum) {
=======
    private int mTaskID;
    private int mTaskCounter;

    public TaskModel(String taskName, int taskDescription, Bitmap taskIcon, int taskPoints, int taskCounter, int taskID) {
>>>>>>> 02889c89017868a4f47b0b8e3f337d489981db33
        this.mTaskName = taskName;
        this.mTaskDescription = taskDescription;
        this.mTaskIcon = taskIcon;
        this.mTaskPoints = taskPoints;
        this.mIsCompleted = false;
        this.mTaskCounter = taskCounter;
<<<<<<< HEAD
        this.mTaskLevelNum = levelNum;
        mTaskID = UUID.randomUUID();
=======
        this.mTaskID = taskID;
>>>>>>> 02889c89017868a4f47b0b8e3f337d489981db33
    }

    public String getTaskName() {
        return mTaskName;
    }

    public int getTaskDescription() {
        return mTaskDescription;
    }

    public Bitmap getTaskIcon() {
        return mTaskIcon;
    }

    public int getTaskPoints() {
        return mTaskPoints;
    }

    public int getTaskCounter() { return mTaskCounter; }

    public int getTaskLevelNum() {return  mTaskLevelNum; }

    public void incrementTaskCounter() { mTaskCounter++; }

    public boolean isCompleted() {
        return mIsCompleted;
    }

    public int getTaskID() {
        return mTaskID;
    }

    public void setIsCompleted(Boolean mIsCompleted) {
        this.mIsCompleted = mIsCompleted;
    }

    public void setTaskName(String name) {
        this.mTaskName = name;
    }


}