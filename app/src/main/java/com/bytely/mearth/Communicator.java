package com.bytely.mearth;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by juice on 3/14/15.
 */
public interface Communicator {
    void runLevelOne();
    void runLevelTwo();
    void runLevelThree();
    TaskModel[] getTaskArray(int fragmentId);
    void updateActionBar();
    void hideUnderlineViews();
    void showUnderlineView(int position);
    ArrayList<Bitmap> getGalleryBitmaps();
}
