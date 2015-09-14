package com.bytely.mearth;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by yaya on 9/8/15.
 */
public abstract class PreferenceTasksUtility {
    public static final String TASK_ID_KEY = "task_completed_id";


    //adding a task as a string into the preference
    public static boolean addTaskId(Activity activity,String taskid){

       String existingTasksIdList = getStringFromPreferences(activity,null,TASK_ID_KEY);

        Log.d("PreferTaskUtility id", " " +  existingTasksIdList);

        // Append new task item to existing list
        if(existingTasksIdList != null){
            existingTasksIdList = existingTasksIdList + "," + taskid;
        }
        else{
            existingTasksIdList = taskid;
            Log.d("PreferTaskUtility id", "else stament");

        }
        // Save in Shared Preferences
        return putStringPreferences(activity, existingTasksIdList, TASK_ID_KEY);

    }

    public static int[] getTaskList(Activity activity){

        String taskId = getStringFromPreferences(activity,null,TASK_ID_KEY);

        return convertToArray(taskId);

    }

    //send taskId to Preferences
    private static boolean putStringPreferences(Activity activity,String nick,String key){

        Log.d("PreferTaskUtility id", "putStringPre");
        SharedPreferences sharedPreferences = activity.getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, nick);
        editor.commit();
        Log.d("PutTasks in method", nick);
        return true;
    }

    //retrieve taskId from Preferences as string  to later convert them into ints
    private static String getStringFromPreferences(Activity activity,String defaultValue,String key){

        SharedPreferences sharedPreferences = activity.getPreferences(Activity.MODE_PRIVATE);
        String temp = sharedPreferences.getString(key, defaultValue);
        return temp;
    }

    private static int[] convertToArray(String str){
        if(str != null) {
            String[] arr = str.split(",");
            int[] intArray = new int[arr.length];

            for (int i = 0; i < arr.length; i++) {
                intArray[i] = Integer.parseInt(arr[i]);

            }
            return intArray;
        } else {
            return null;
        }
    }
}
