package com.bytely.mearth;

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
}
