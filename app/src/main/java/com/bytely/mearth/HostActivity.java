

package com.bytely.mearth;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.parse.Parse;
import com.parse.ParseObject;


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
            showUnderlineView(0);
            dashFragment = new DashFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, dashFragment, "dash_fragment");
            fragmentTransaction.commit();
        }

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
                        new TaskModel("Recycle Items", 2, mRecyclingBitmap, 200, 0),
                        new TaskModel("Turn Off Room Lights", 2, mLightBitmap, 100, 0),
                        new TaskModel("Turn Off Running Water", 2, mWaterBitmap, 100, 0),
                        new TaskModel("Don't Use Single-Use Bottles", 2, mWaterBottleBitmap, 300, 0),
                        new TaskModel("Walk or Ride a Bike", 2, mWalkBitmap, 400, 0),
                };

                mLevelTwoArray = new TaskModel[]{
                        new TaskModel("Start a Food Garden", 2, mPaleBitmap, 600, 0),
                        new TaskModel("Grow Native Plants", 2, mPreserveBitmap, 800, 0),
                        new TaskModel("Use Eco-Friendly Cleaning Supplies", 2, mPreserveBitmap, 500, 0),
                        new TaskModel("Switch to Fluorescent Light Bulbs", 2, mLightBitmap, 600, 0),
                        new TaskModel("Go Shopping at Farmers Market", 2, mSolarBitmap, 700, 0),
                };

                mLevelThreeArray = new TaskModel[]{
                        new TaskModel("Get School to Adopt Green Policy", 2, mWaterBitmap, 10000, 0),
                        new TaskModel("Invite Someone to a MEarth Event", 2, mRecyclingBitmap, 5000, 0),
                        new TaskModel("Plant Trees", 2, mWildernessBitmap, 8000, 0),
                        new TaskModel("Tell Someone to Install MEarth App", 2, mSolarBitmap, 2000, 0),
                        new TaskModel("Organize a Beach Cleanup", 2, mWalkBitmap, 7000, 0),
                        new TaskModel("Start a Recycling Club", 2, mRecyclingBitmap, 6000, 0),

                };
                Log.d("Thread", "Done");
            }
        });

        thread.start();

        Log.d("Activity", "After Thread");

        mAboutButton = (ImageButton) findViewById(R.id.about_button);
        mAboutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //startActivity(new Intent(MainActivity.this, AboutActivity.class));

                Fragment aboutFragment = fragmentManager.findFragmentByTag("about_fragment");

                if(aboutFragment == null && savedInstanceState == null) {
                    hideUnderlineViews();
                    showUnderlineView(3);
                    aboutFragment = new AboutFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, aboutFragment);
                    fragmentTransaction.addToBackStack("about_fragment");
                    Log.d("Fragment Transaction", "Added to backstack");
                    fragmentTransaction.commit();
                }
            }
        });

        mProfileButton = (ImageButton) findViewById(R.id.profile_button);
        mProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment profileFragment = fragmentManager.findFragmentByTag("profile_fragment");

                if (profileFragment == null && savedInstanceState == null) {
                    hideUnderlineViews();
                    showUnderlineView(2);
                    profileFragment = new ProfileFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, profileFragment);
                    fragmentTransaction.addToBackStack("profile_fragment");
                    Log.d("Fragment Transaction", "Added to backstack");
                    fragmentTransaction.commit();
                }


            }
        });

        mLevelsButton = (ImageButton) findViewById(R.id.levels_button);
        mLevelsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment levelsFragment = fragmentManager.findFragmentByTag("levels_fragment");

                if(levelsFragment == null && savedInstanceState == null) {
                    hideUnderlineViews();
                    showUnderlineView(1);
                    levelsFragment = new LevelsFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, levelsFragment);
                    fragmentTransaction.addToBackStack("levels_fragment");
                    Log.d("Fragment Transaction", "Added to backstack");
                    fragmentTransaction.commit();
                }
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

        // Enable Local Datastore.
        // Parse.enableLocalDatastore(this);

        Parse.initialize(this, "4Y23J3va4EQH7LsYM4dPxuv4cXgCrFL2REIQQseE", "R4Bv1mOQhTb94vgUYoYPpUh0XzoW7X58j8D4MIwP");
        // Test Database
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

    }

    @Override
    public void onSaveInstanceState(Bundle savedState) {

    }

    @Override
    public void runLevelOne() {
        Fragment levelOneFragment = fragmentManager.findFragmentByTag("level_one");

        if(levelOneFragment == null) {
            levelOneFragment = TaskListFragment.getInstance(1);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, levelOneFragment);
            fragmentTransaction.addToBackStack("level_one");
            fragmentTransaction.commit();
        }
    }

    @Override
    public void runLevelTwo() {
        Fragment levelTwoFragment = fragmentManager.findFragmentByTag("level_two");

        if(levelTwoFragment == null) {
            levelTwoFragment = TaskListFragment.getInstance(2);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, levelTwoFragment);
            fragmentTransaction.addToBackStack("level_two");
            fragmentTransaction.commit();
        }
    }

    @Override
    public void runLevelThree() {
        Fragment levelThreeFragment = fragmentManager.findFragmentByTag("level_three");

        if(levelThreeFragment == null) {
            levelThreeFragment = TaskListFragment.getInstance(3);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, levelThreeFragment);
            fragmentTransaction.addToBackStack("level_three");
            fragmentTransaction.commit();
        }
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
}
