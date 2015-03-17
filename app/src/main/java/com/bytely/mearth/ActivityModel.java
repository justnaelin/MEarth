package com.bytely.mearth;

import android.graphics.Bitmap;


public class ActivityModel {
    private String mActivityName;
    private int mActivityDescription;
    private Bitmap mActivityIcon;
    private int mActivityPoints;
    private boolean mIsCompleted;

    public ActivityModel(String s, int description, int i) {
        this.mActivityName = s;
        this.mActivityDescription = description;
        this.mActivityPoints = i;
        this.mIsCompleted = false;
    }

    public ActivityModel(String s, int i, Bitmap mRecyclingBitmap, int i1) {
        this.mActivityName = s;
        this.mActivityDescription = i;
        this.mActivityIcon = mRecyclingBitmap;
        this.mActivityPoints = i1;
        this.mIsCompleted = false;
    }

    public void ActivityModel(String mActivityName, int mActivityDescription,
                              Bitmap mActivityIcon, int mActivityPoints) {

    }


    public String getActivityName() {
        return mActivityName;
    }

    public int getActivityDescription() {
        return mActivityDescription;
    }

    public Bitmap getActivityIcon() {
        return mActivityIcon;
    }

    public int getActivityPoints() {
        return mActivityPoints;
    }

    public boolean isCompleted() {
        return mIsCompleted;
    }


    public void setIsCompleted(Boolean mIsCompleted) {
        this.mIsCompleted = mIsCompleted;
    }
}
