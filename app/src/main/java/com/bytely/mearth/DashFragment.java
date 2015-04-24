package com.bytely.mearth;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashFragment extends Fragment {
    private Communicator comm;
    private ImageView imageView;
    private TextView titleText;
    private TextView pointView;
    private ArrayList<TaskModel> mTaskList;

    public DashFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        comm = (Communicator) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Dash", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("Dash", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_dash, container, false);

        mTaskList = DashboardTasks.getInstance(getActivity()).getTaskList();

        imageView = (ImageView) view.findViewById(R.id.current_image);
        titleText = (TextView) view.findViewById(R.id.current_title);
        pointView = (TextView) view.findViewById(R.id.current_points);

        if(mTaskList.size() != 0) {
            imageView.setImageBitmap(mTaskList.get(0).getTaskIcon());
            titleText.setText(mTaskList.get(0).getTaskName());
        }


        /*
        if(task != null) {
            imageView.setImageBitmap(task.getTaskIcon());
            titleText.setText(task.getTaskName());
            //pointView.setText(task.getTaskPoints());
        }
        */

        /*
        mCurrentImage = (ImageView) view.findViewById(R.id.current_image);
        mCurrentText = (TextView) view.findViewById(R.id.current_title);
        mCurrentPoints = (TextView) view.findViewById(R.id.current_points);

        Bitmap currentIm = getRoundedShape(R.drawable.pale);

        mCurrentImage.setImageBitmap(currentIm);

        mCurrentText.setText("Grow Native Grows: ");
        */

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Dash", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Dash", "onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Dash", "onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Dash", "onDestroy");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Dash", "onStop");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Dash", "onStart");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Dash", "onDestroyView");
    }

    public Bitmap getRoundedShape(int imageResource) {
        Bitmap scaleBitmapImage = BitmapFactory.decodeResource(getResources(), imageResource);
        int targetWidth = 200;
        int targetHeight = 200;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth, targetHeight), null);
        return targetBitmap;
    }

}
