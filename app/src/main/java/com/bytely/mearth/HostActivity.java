

package com.bytely.mearth;


import android.annotation.TargetApi;
import android.content.res.Resources;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.io.File;
import java.util.ArrayList;


public class HostActivity extends AppCompatActivity implements Communicator {

    private ImageButton mAboutButton;//the about button
    private ImageButton mLevelsButton;
    private ImageButton mProfileButton;
    private ImageButton mDashButton;
    private Bitmap mRecyclingBitmap;
    private Bitmap mLightBitmap;
    private Bitmap mWaterBitmap;
    private Bitmap mWaterBottleBitmap;
    private Bitmap mWalkBitmap;
    private Bitmap mPaleBitmap;
    private Bitmap mPreserveBitmap;
    private Bitmap mSolarBitmap;
    private Bitmap mWildernessBitmap;
    private Bitmap mGreenEnergyBitmap;
    private TaskModel[] mLevelOneArray;
    private TaskModel[] mLevelTwoArray;
    private TaskModel[] mLevelThreeArray;
    private final android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
    private ArrayList<Bitmap> galleryBitmaps;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        hideUnderlineViews();

        getWindow().setBackgroundDrawable(null);

        if(savedInstanceState == null) {
            Log.d("onCreate", "New activity instance");
        }

        DashFragment dashFragment = null;

        if(savedInstanceState == null) {

            dashFragment = new DashFragment();
        }
        showUnderlineView(0);
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, dashFragment, "dash_fragment");
        fragmentTransaction.commit();

        loadTaskObjects();
        loadBitmapsIntoMemory();


        Log.d("Activity", "After Thread");

        mAboutButton = (ImageButton) findViewById(R.id.about_button);
        mAboutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //startActivity(new Intent(MainActivity.this, AboutActivity.class));

                Fragment aboutFragment = fragmentManager.findFragmentByTag("about_fragment");

                if(aboutFragment == null) {
                    aboutFragment = new AboutFragment();
                    Log.d("Fragment", "Inside if");
                }
                hideUnderlineViews();
                showUnderlineView(3);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, aboutFragment, "about_fragment");
                fragmentTransaction.addToBackStack("about_fragment");
                Log.d("Fragment Transaction", "Added to backstack");
                fragmentTransaction.commit();

            }
        });

        mProfileButton = (ImageButton) findViewById(R.id.profile_button);
        mProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment profileFragment = fragmentManager.findFragmentByTag("profile_fragment");

                if (profileFragment == null) {
                    profileFragment = new ProfileFragment();

                    Log.d("Fragment", "Inside if");
                }
                hideUnderlineViews();
                showUnderlineView(2);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, profileFragment, "profile_fragment");
                fragmentTransaction.addToBackStack("profile_fragment");
                Log.d("Fragment Transaction", "Added to backstack");
                fragmentTransaction.commit();



            }
        });

        mLevelsButton = (ImageButton) findViewById(R.id.levels_button);
        mLevelsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LevelsFragment levelsFragment = (LevelsFragment)fragmentManager.findFragmentByTag("levels_fragment");
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if(levelsFragment == null) {
                    levelsFragment = new LevelsFragment();

                    Log.d("Fragment", "Inside if");

                }

                hideUnderlineViews();
                showUnderlineView(1);
                fragmentTransaction.replace(R.id.fragment_container, levelsFragment, "levels_fragment");
                fragmentTransaction.addToBackStack("levels_fragment");
                Log.d("Fragment Transaction", "Added to backstack");
                fragmentTransaction.commit();

            }
        });

        mDashButton = (ImageButton) findViewById(R.id.dash_button);
        final Fragment finalDashFragment = dashFragment;
        mDashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideUnderlineViews();
                showUnderlineView(0);
                //Toast.makeText(getApplicationContext(), "Dash Clicked", Toast.LENGTH_SHORT).show();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, finalDashFragment);
                fragmentTransaction.addToBackStack("dash_fragment");
                Log.d("Fragment Transaction", "Added to backstack");
                fragmentTransaction.commit();

            }
        });
    }

    public void loadTaskObjects() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                Log.d("Thread", "Background");

                mRecyclingBitmap = FormatIcon.getRoundedShape(getApplicationContext(), R.drawable.recycle);
                mLightBitmap = FormatIcon.getRoundedShape(getApplicationContext(), R.drawable.lightbulb);
                mWaterBitmap = FormatIcon.getRoundedShape(getApplicationContext(), R.drawable.eco_cleaning);
                mWaterBottleBitmap = FormatIcon.getRoundedShape(getApplicationContext(), R.drawable.water_drop);
                mWalkBitmap = FormatIcon.getRoundedShape(getApplicationContext(), R.drawable.walk);
                mPaleBitmap = FormatIcon.getRoundedShape(getApplicationContext(), R.drawable.pale);
                mPreserveBitmap = FormatIcon.getRoundedShape(getApplicationContext(), R.drawable.preserve);
                mSolarBitmap = FormatIcon.getRoundedShape(getApplicationContext(), R.drawable.solar);
                mWildernessBitmap = FormatIcon.getRoundedShape(getApplicationContext(), R.drawable.wilderness);

                mLevelOneArray = new TaskModel[]{

                        new TaskModel("Recycle Items", 2, mRecyclingBitmap, 200, 0, 1, 1),
                        new TaskModel("Turn Off Room Lights", 2, mLightBitmap, 100, 0, 1, 2),
                        new TaskModel("Turn Off Running Water", 2, mWaterBitmap, 100, 0, 1, 3),
                        new TaskModel("Don't Use Single-Use Bottles", 2, mWaterBottleBitmap, 300, 0, 1, 4),
                        new TaskModel("Walk or Ride a Bike", 2, mWalkBitmap, 400, 0, 1, 5),
                };

                mLevelTwoArray = new TaskModel[]{
                        new TaskModel("Start a Food Garden", 2, mPaleBitmap, 600, 0, 2, 6),
                        new TaskModel("Grow Native Plants", 2, mPreserveBitmap, 800, 0, 2, 7),
                        new TaskModel("Use Eco-Friendly Cleaning Supplies", 2, mPreserveBitmap, 500, 0, 2, 8),
                        new TaskModel("Switch to Fluorescent Light Bulbs", 2, mLightBitmap, 600, 0, 2, 9),
                        new TaskModel("Go Shopping at Farmers Market", 2, mSolarBitmap, 700, 0, 2, 10),
                };

                mLevelThreeArray = new TaskModel[]{
                        new TaskModel("Get School to Adopt Green Policy", 2, mWaterBitmap, 10000, 0, 3, 11),
                        new TaskModel("Invite Someone to a MEarth Event", 2, mRecyclingBitmap, 5000, 0, 3, 12),
                        new TaskModel("Plant Trees", 2, mWildernessBitmap, 8000, 0, 3, 13),
                        new TaskModel("Tell Someone to Install MEarth App", 2, mSolarBitmap, 2000, 0, 3, 14),
                        new TaskModel("Organize a Beach Cleanup", 2, mWalkBitmap, 7000, 0, 3, 15),
                        new TaskModel("Start a Recycling Club", 2, mRecyclingBitmap, 6000, 0, 3, 16),

                };
                Log.d("Thread", "Done");
            }
        });

        thread.start();
    }

    @Override
    public void onSaveInstanceState(Bundle savedState) {

    }

    @Override
    public void runLevelOne() {
        Fragment levelOneFragment = fragmentManager.findFragmentByTag("level_one");

        if(levelOneFragment == null) {
            levelOneFragment = TaskListFragment.getInstance(1);

        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, levelOneFragment, "level_one");
        fragmentTransaction.addToBackStack("level_one");
        fragmentTransaction.commit();
    }

    @Override
    public void runLevelTwo() {
        Fragment levelTwoFragment = fragmentManager.findFragmentByTag("level_two");

        if(levelTwoFragment == null) {
            levelTwoFragment = TaskListFragment.getInstance(2);

        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, levelTwoFragment, "levels");
        fragmentTransaction.addToBackStack("level_two");
        fragmentTransaction.commit();
    }

    @Override
    public void runLevelThree() {
        Fragment levelThreeFragment = fragmentManager.findFragmentByTag("level_three");

        if(levelThreeFragment == null) {
            levelThreeFragment = TaskListFragment.getInstance(3);

        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, levelThreeFragment, "levels");
        fragmentTransaction.addToBackStack("level_three");
        fragmentTransaction.commit();
    }

    @Override
    public TaskModel[] getTaskArray(int fragmentId) {
        switch (fragmentId) {
            case 1:
                return mLevelOneArray;
            case 2:
                return mLevelTwoArray;
            case 3:
                return mLevelThreeArray;
        }
        return null;
    }

    @Override
    public void updateActionBar() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //super.onActivityResult(requestCode, resultCode, data);


        //result code -1 = image was taken
        if (resultCode ==  -1){

            Log.d("Host", "Image was taken =  " + Integer.toString(resultCode));

            //requestCode 2 = intent send from profile camera fragment
            if (requestCode == 2 ) {

                Log.d("Host", "Image was from profile camera =  " + Integer.toString(requestCode));
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                fragment.onActivityResult(requestCode, resultCode, data);
            }


            //requestCode 3 = intent send from level confirm Dialog fragment
            else if(requestCode == 3){
                Log.d("Host", "Image was from level Dialog =  " + Integer.toString(requestCode));
                TaskListFragment level = (TaskListFragment) fragmentManager
                        .findFragmentByTag("levels");
                // Log.d("Current Fragment", level.getClass().getSimpleName());
                level.printMessage();
                level.addPoints();

            }

        }

        //result code 0 = image was not taken
        else{
            Log.d("Host", "No Image  Taken =  " + Integer.toString(resultCode));

        }

    }

    public void hideUnderlineViews() {
        View linView = this.findViewById(R.id.linear_layout_underline);

        for(int i = 0; i < ((ViewGroup)linView).getChildCount(); i++) {
            View childView = ((ViewGroup)linView).getChildAt(i);
            childView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showUnderlineView(int position) {
        View linView = this.findViewById(R.id.linear_layout_underline);

        for(int i = 0; i < ((ViewGroup)linView).getChildCount(); i++) {
            if(i == position) {
                View childView = ((ViewGroup)linView).getChildAt(i);
                childView.setVisibility(View.VISIBLE);
            }

        }
    }

    private void loadBitmapsIntoMemory() {

        final ArrayList<String> images_file_path = new ArrayList<String>();
        final ArrayList<Bitmap> bitmap_images = new ArrayList<>();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);



                setGalleryImages(images_file_path);
                setBitmap_images(images_file_path, bitmap_images);
            }
        });

        thread.start();

        galleryBitmaps = bitmap_images;
    }

    @Override
    public ArrayList<Bitmap> getGalleryBitmaps() {
        return galleryBitmaps;
    }

    public void setGalleryImages(ArrayList<String> images_file_path){
        File[] listFile;

        File file = new File(android.os.Environment.getExternalStorageDirectory(),"mearth");

        if(file.isDirectory()){
            listFile = file.listFiles();

            for (int i = 0; i < listFile.length; i++)
            {

                images_file_path.add(listFile[i].getAbsolutePath());
            }
        }
    }

    public void setBitmap_images(ArrayList<String> images_file_path, ArrayList<Bitmap> bitmap_images){

        for (int i = 0; i < images_file_path.size(); i++)
        {
            //bitmap_images.add(BitmapFactory.decodeFile(images_file_path.get(i)));
            bitmap_images.add(loadBitmap(images_file_path.get(i)));

        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public Bitmap loadBitmap(String bitmapFilePath) {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x / 3;
        int height = width;

        Resources r = Resources.getSystem();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                60, r.getDisplayMetrics());

        Log.d("Pixels", "Pixels: " + px);

        return decodeSampledBitmap(bitmapFilePath, width, height);
    }

    public static Bitmap decodeSampledBitmap(String filePath, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
