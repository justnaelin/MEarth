package com.bytely.mearth;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


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

    ImageView mImageView;
    static final int REQUEST_TAKE_PHOTO = 0;

    ArrayList<Bitmap> photoGallery = new ArrayList<>();


    public ProfileFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comm = (Communicator) getActivity();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);


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
                Fragment currentFragment = new CurrentlyFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.profile_fragment_container, currentFragment);
                fragmentTransaction.addToBackStack("profile_current_fragment");
                fragmentTransaction.commit();
            }
        });

        mGoalsButton = (ImageButton) view.findViewById(R.id.goals_button);
        mGoalsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Fragment goalsFragment = new GalleryFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.profile_fragment_container, goalsFragment);
                fragmentTransaction.addToBackStack("profile_goals_fragment");
                fragmentTransaction.commit();
            }
        });

        mCameraButton = (ImageButton) view.findViewById(R.id.camera_button);
        mCameraButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //dispatchTakePictureIntent();
                process();
            }
        });

        // Displays user's total points in profile fragment
        TextView userPoints = (TextView) view.findViewById(R.id.user_points);
        userPoints.setText(Integer.toString((DashboardTasks.getInstance(getActivity()).getPoints())));

        return view;
    }

    public void process(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, 0);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 0)
        {
            switch(resultCode){
                case Activity.RESULT_OK: {
                   // Bundle extras = data.getExtras();
                   // Bitmap imageBitmap = (Bitmap) extras.get("data");
                   // mImageView.setImageBitmap(imageBitmap);

                    dispatchTakePictureIntent(data);
                    //Toast toast = Toast.makeText(getActivity().getApplicationContext(), mCurrentPhotoPath, Toast.LENGTH_LONG);
                    //toast.show();
                    //Bitmap image = (Bitmap) data.getExtras().get("data");
                   // photoGallery.add(image);
                   // for(int i = 0; i < photoGallery.size(); i++){
                    //    Log.d(("Tag"), photoGallery.get(i).toString() + " was added! ");}

                }
                break;
                case Activity.RESULT_CANCELED:
                    break;
                default:
                    break;
            }
        }
    }

    private void dispatchTakePictureIntent(Intent takePictureIntent) {
      //  Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent

        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) == null) {
            // Create the File where the photo should go

            File photoFile = null;
            try {

                photoFile = createImageFile();
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Toast", Toast.LENGTH_LONG);
                toast.show();

            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    //Save the picture
    private File createImageFile() throws IOException {
        // Create an image file name


        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );


        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();

        return image;
    }



}

