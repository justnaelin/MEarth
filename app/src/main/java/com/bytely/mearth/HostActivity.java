package com.bytely.mearth;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class HostActivity extends ActionBarActivity implements Communicator {
    private ImageButton mAboutButton;//the about button
    private ImageButton mLevelsButton;
    private ImageButton mProfileButton;
    private ImageButton mDashButton;
    private ImageButton button;
    private ImageView mCurrentImage;
    private TextView mCurrentText;
    private TextView mCurrentPoints;
    private Bitmap mRecyclingBitmap;
    private Bitmap mLightBitmap;
    private Bitmap mWaterBitmap;
    private Bitmap mWaterBottleBitmap;
    private Bitmap mWalkBitmap;
    private ActivityModel[] mLevelOneArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        mRecyclingBitmap = getRoundedShape(R.drawable.recycle);
        mLightBitmap = getRoundedShape(R.drawable.lightbulb);
        mWaterBitmap = getRoundedShape(R.drawable.eco_cleaning);
        mWaterBottleBitmap = getRoundedShape(R.drawable.water_drop);
        mWalkBitmap = getRoundedShape(R.drawable.walk);

        mLevelOneArray = new ActivityModel[]{
                new ActivityModel("Recycle Items", 2, mRecyclingBitmap, 20),
                new ActivityModel("Turn Off Room Lights", 2, mLightBitmap, 20),
                new ActivityModel("Turn Off Running Water", 2, mWaterBitmap, 20),
                new ActivityModel("Don't Use One-Use Bottles", 2, mWaterBottleBitmap, 20),
                new ActivityModel("Walk or Ride a Bike", 2, mWalkBitmap, 20),
        };

        mAboutButton = (ImageButton) findViewById(R.id.about_button);
        mAboutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //startActivity(new Intent(MainActivity.this, AboutActivity.class));

            }
        });

        mProfileButton = (ImageButton) findViewById(R.id.profile_button);
        mProfileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });


        mLevelsButton = (ImageButton) findViewById(R.id.levels_button);
        mLevelsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment levelsFragment = new LevelsFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, levelsFragment);
                fragmentTransaction.addToBackStack("levels_fragment");
                fragmentTransaction.commit();
            }
        });

        mDashButton = (ImageButton) findViewById(R.id.dash_button);
        mDashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment dashFragment = new DashFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, dashFragment);
                fragmentTransaction.addToBackStack("dash_fragment");
                fragmentTransaction.commit();
            }
        });


        Fragment dashFragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if(dashFragment == null) {
            dashFragment = new DashFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, dashFragment);
            fragmentTransaction.commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_host, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void runLevelOne() {
        Fragment levelOneFragment = new ActivityListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, levelOneFragment);
        fragmentTransaction.addToBackStack("level_one");
        fragmentTransaction.commit();
    }

    @Override
    public void runLevelTwo() {

    }

    @Override
    public void runLevelThree() {

    }

    @Override
    public ActivityModel[] getActivityArray() {
        return mLevelOneArray;
    }

    @Override
    public void updateActionBar() {

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
