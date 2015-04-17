package com.bytely.mearth;

import java.util.ArrayList;

/**
 * Created by juice on 4/10/15.
 */
public interface TaskAdapterCommunicator {
    public void addTask(TaskModel task);
    public ArrayList<TaskModel> getTaskList();
}
