package com.bytely.mearth;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;


public class LevelScreen extends ActionBarActivity implements Communicator{
    private FragmentManager fragmentManager;
    private ActionBar mActionBar;
    private List<ActivityModel> mActivityArray;
    private Bitmap mRecyclingBitmap;
    private Bitmap mLightBitmap;
    private Bitmap mWaterBitmap;
    private Bitmap mWaterBottleBitmap;
    private Bitmap mWalkBitmap;
    private ActivityModel[] mLevelOneArray;
    private ActivityModel[] mLevelTwoArray;
    private ActivityModel[] mLevelThreeArray;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_screen);

        mActionBar = getSupportActionBar();
        mActionBar.setTitle("Levels");



        //int[] levelOneRes = {R.drawable.recycle, R.drawable.lightbulb, R.drawable.eco_cleaning,
                //R.drawable.water_drop, R.drawable.walk};
        //DownloadImageTask imageTask = new DownloadImageTask();
        //imageTask.execute(levelOneRes);


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


        fragmentManager = getSupportFragmentManager();
        Fragment levelFragment = fragmentManager.findFragmentById(R.id.level_container);

        if(levelFragment == null) {
            levelFragment = new LevelsFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.level_container, levelFragment);
            transaction.commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level_screen, menu);
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
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Level 1");
        //mActivityArray = new ArrayList<ActivityModel>(Arrays.asList(mLevelOneArray));

        Fragment levelOneFragment = new ActivityListFragment(mLevelOneArray);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.level_container, levelOneFragment);
        fragmentTransaction.addToBackStack("level_one_fragment");
        fragmentTransaction.commit();


    }

    @Override
    public void runLevelTwo() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Level 2");

        Fragment levelTwoFragment = new ActivityListFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.level_container, levelTwoFragment);
        fragmentTransaction.addToBackStack("level_two_fragment");
        fragmentTransaction.commit();
    }

    @Override
    public void runLevelThree() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Level 3");

        Fragment levelThreeFragment = new ActivityListFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.level_container, levelThreeFragment);
        fragmentTransaction.addToBackStack("level_three_fragment");
        fragmentTransaction.commit();
    }


    @Override
    public void updateActionBar() {
        mActionBar.setTitle("Levels");
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

    /*
    public class DownloadImageTask extends AsyncTask<int[], Void, Bitmap[]> {
        //public Bitmap mIcon;
        Bitmap[] bitmaps;

        @Override
        protected Bitmap[] doInBackground(int... urls) {
            //String urldisplay = urls[0];

            //ArrayList<Bitmap> bitmaps = new ArrayList<>();

            try {
                //String picURL = urls[0];
                for(int i = 0; i < count ; i++) {
                    java.net.URL url = new java.net.URL(urls[i]);
                    HttpURLConnection connection = (HttpURLConnection) url
                            .openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream in = connection.getInputStream();
                    //mIcon = BitmapFactory.decodeStream(in);
                    mIcon = BitmapFactory.decodeStream(in);
                    bitArr[i] = mIcon;
                    //bitmaps.add(mIcon);
                }
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }

            return bitmaps;
        }

        @Override
        protected void onPostExecute(Bitmap[] result) {
            //picBitmap = result;
            //for(int i = 0; i < result.size(); i++) {
            //mDishBitmap.add(result.get(i));
            //}
            super.onPostExecute(result);
            for(int i = 0; i < result.length; i++) {
                mDishBitmap.add(result[i]);
            }

        }
    }
    */
}
