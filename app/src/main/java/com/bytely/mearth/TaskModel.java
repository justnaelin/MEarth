package com.bytely.mearth;

import android.graphics.Bitmap;
import java.util.UUID;


public class TaskModel {
    private String mTaskName;
    private int mTaskDescription;
    private Bitmap mTaskIcon;
    private int mTaskPoints;
    private boolean mIsCompleted;
    private UUID mTaskID;
    private int mTaskCounter;

    public TaskModel(String s, int i, Bitmap icon, int i1, int i2) {
        this.mTaskName = s;
        this.mTaskDescription = i;
        this.mTaskIcon = icon;
        this.mTaskPoints = i1;
        this.mIsCompleted = false;
        this.mTaskCounter = i2;
        mTaskID = UUID.randomUUID();
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

    public void incrementTaskCounter() { mTaskCounter++; }

    public boolean isCompleted() {
        return mIsCompleted;
    }

    public UUID getTaskID() {
        return mTaskID;
    }

    public void setIsCompleted(Boolean mIsCompleted) {
        this.mIsCompleted = mIsCompleted;
    }

    public void setTaskName(String name) {
        this.mTaskName = name;
    }
}