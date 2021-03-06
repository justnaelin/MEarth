package com.bytely.mearth;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private Communicator comm;

    private ImageButton mAchieveButton;
    private ImageButton mCurrentButton;
    private ImageButton mGoalsButton;
    private ImageButton mCameraButton;
    String mCurrentPhotoPath;
    private File directory = null;

    public static String imageFilePath;
    public File imageFile;

    ImageView mImageView;
    static final int REQUEST_TAKE_PHOTO = 0;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comm = (Communicator) getActivity();

        directory = new File(Environment.getExternalStorageDirectory()+"/mearth"); //the string to a path
        File file = new File(Environment.getExternalStorageDirectory()+"/mearth");

        if(!(file.exists() && file.isDirectory())){
            directory.mkdirs();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        comm.hideUnderlineViews();
        comm.showUnderlineView(2);

        final FragmentManager fragmentManager = getChildFragmentManager();
        final Fragment achieveFragment = new AchieveFragment();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.profile_fragment_container, achieveFragment);
        fragmentTransaction.commit();

        mAchieveButton = (ImageButton) view.findViewById(R.id.achieve_button);
        mAchieveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Fragment achieveFragment = new AchieveFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.profile_fragment_container, achieveFragment);
                fragmentTransaction.addToBackStack("profile_achieve_fragment");
                fragmentTransaction.commit();
            }
        });

        mCurrentButton = (ImageButton) view.findViewById(R.id.current_button);
        mCurrentButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Fragment currentFragment = new CurrentFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.profile_fragment_container, currentFragment);
                fragmentTransaction.addToBackStack("profile_current_fragment");
                fragmentTransaction.commit();
            }
        });

        mGoalsButton = (ImageButton) view.findViewById(R.id.goals_button);
        mGoalsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){/*
                GalleryFragment goalsFragment;
                goalsFragment = (GalleryFragment) fragmentManager.findFragmentByTag("profile_goals_fragment");
                if(goalsFragment == null) {
                    goalsFragment = new GalleryFragment();
                }*/

                //*****************

                 File[] listFile;

                 File file = new File(android.os.Environment.getExternalStorageDirectory(),"mearth");

                 if(file.isDirectory())
                 {
                     listFile = file.listFiles();
                     Log.d("Toast Stuff", " " + listFile.length );
                     if(listFile.length > 0) {
                         Toast.makeText(getActivity(), "Loading Images. . .", Toast.LENGTH_SHORT).show();
                     }
                 }
                //*****************

                Log.d("gallery onClick", "clicked");
                Fragment galleryFragment = new GalleryFragment();


                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.profile_fragment_container, galleryFragment, "profile_goals_fragment");
                fragmentTransaction.addToBackStack("profile_goals_fragment");
                fragmentTransaction.commit();
            }
        });

        mCameraButton = (ImageButton) view.findViewById(R.id.camera_button);
        mCameraButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                process();
            }
        });

        // Displays user's total points in profile fragment
        TextView userPoints = (TextView) view.findViewById(R.id.user_points);
        userPoints.setText(Integer.toString((DashboardTasks.getInstance(getActivity()).getPoints())));

        return view;
    }

    public void process(){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        //This creates the image file path and the name of each picture
        imageFilePath = Environment.getExternalStorageDirectory() + "/mearth/IMG" + System.currentTimeMillis() + ".png";

        imageFile = new File(imageFilePath);

        Uri imageFileUri = Uri.fromFile(imageFile);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);

        getActivity().startActivityForResult(intent, 2);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){

        FragmentManager fm = getChildFragmentManager();
        GalleryFragment galleryFragment = (GalleryFragment) fm.findFragmentByTag("profile_goals_fragment");
        if(galleryFragment != null)
            galleryFragment.updateAdapter();
        Log.d("Profile-Camera", "Inside profile");


    }




}

