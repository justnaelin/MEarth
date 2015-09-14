package com.bytely.mearth;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * Created by yaya on 6/14/15.
 */
public class ConfirmPointsDialogFragment extends DialogFragment {

    private static final String POINTS_ADDED_KEY = "points_to_be_added";

    private int mPointsToAdd;

    private static TaskModel sTaskClicked;

    int userPoints = DashboardTasks.getInstance(getActivity()).getPoints();

    Toast mBadgeToast;

    public ConfirmPointsDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mPointsToAdd = getArguments().getInt(POINTS_ADDED_KEY);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.fragment_confirm_points_dialog, null);

        builder.setTitle("Confirm Task Completion");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                DashboardTasks.getInstance(getActivity()).addPoints(mPointsToAdd);
                DashboardTasks.getInstance(getActivity()).levelNotification();
                DashboardTasks.getInstance(getActivity()).badgeNotification();
                DashboardTasks.getInstance(getActivity()).addTask(sTaskClicked);
                sTaskClicked.incrementTaskCounter();

                sendResult(Activity.RESULT_OK);


            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
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


    public static ConfirmPointsDialogFragment getInstance(int pointsToAdd, TaskModel task) {
        sTaskClicked = task;

        ConfirmPointsDialogFragment pointsDialogFragment = new ConfirmPointsDialogFragment();
        Bundle args = new Bundle();

        args.putInt(POINTS_ADDED_KEY, pointsToAdd);

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

}
