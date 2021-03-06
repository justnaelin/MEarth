package com.bytely.mearth;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmPictureDialogFragment extends DialogFragment {

    private static final String POINTS_ADDED_KEY = "points_to_be_added";

    private int mPointsToAdd;

    private static TaskModel sTaskClicked;

    private File directory = null;

    public static String imageFilePath;
    public static File imageFile;

    public static final int BASE_RESULT_RCODE = 111;

    //retrieve task id
    public static final String TASK_ID_KEY = "task_completed_id";
    private static final String TASKS_FILENAME = "completed_tasks";
    public int mTaskId;
    private static Context sContext;


    public ConfirmPictureDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mPointsToAdd = getArguments().getInt(POINTS_ADDED_KEY);
        mTaskId = getArguments().getInt(TASK_ID_KEY);
    }

//this creates the confirm picture dialog
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.fragment_confirm_picture_dialog, null);

        //gets the directory file path to save the images
        directory = new File(Environment.getExternalStorageDirectory()+"/mearth");
        File file = new File(Environment.getExternalStorageDirectory()+"/mearth");

        //if it has not been created we create it
        if(!(file.exists() && file.isDirectory())){
            directory.mkdirs();
        }

        builder.setTitle("Confirm task, upload a picture");
        builder.setPositiveButton("Upload", new DialogInterface.OnClickListener() {

            //if the confirm task button is selected it launches the camera view
            @Override
            public void onClick(DialogInterface dialog, int which) {

                cameraLaunching();




            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setIcon(R.mipmap.ic_launcher);

        builder.setView(view);

        Dialog dialog = builder.create();

        return dialog;
    }


    public static ConfirmPictureDialogFragment getInstance(int pointsToAdd, TaskModel task, int taskId) {
        sTaskClicked = task;

        ConfirmPictureDialogFragment pointsDialogFragment = new ConfirmPictureDialogFragment();
        Bundle args = new Bundle();

        //send the points to be added as arguments to

        args.putInt(POINTS_ADDED_KEY, pointsToAdd);
        args.putInt(TASK_ID_KEY, taskId);
        pointsDialogFragment.setArguments(args);

        return pointsDialogFragment;
    }

    public void sendResult(int resultCode) {


        if(getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        getTargetFragment().onActivityResult(TaskListFragment.REQUEST_POINTS,
                resultCode, intent);
    }


    public void cameraLaunching() {

        Fragment fragment = this;
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        //This creates the image file path and the name of each picture
        imageFilePath = Environment.getExternalStorageDirectory() + "/mearth/IMG" + System.currentTimeMillis() + ".png";

        imageFile = new File(imageFilePath);

        Uri imageFileUri = Uri.fromFile(imageFile);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);

        // Starts the camera intent wit a request code number
        getActivity().startActivityForResult(intent, 3);


    }



    //Receive the intent Result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //  super.onActivityResult(requestCode, resultCode, data);
       /* Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        fragment.onActivityResult(requestCode, resultCode, data);*/

        //result code -1 = image taken send from the Confirm Dialog Fragment
        //result code 0 = image not taken  send from the Confirm Dialog Fragment

    }

}
