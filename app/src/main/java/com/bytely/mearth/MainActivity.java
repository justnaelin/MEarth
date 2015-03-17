package com.bytely.mearth;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    private ImageButton mAboutButton;//the about button
    private ImageButton mActivityButton;
    private ImageButton mProfileButton;
    private ImageButton mDashButton;
    private ImageButton button;
    private ImageView mCurrentImage;
    private TextView mCurrentText;
    private TextView mCurrentPoints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCurrentImage = (ImageView) findViewById(R.id.current_image);
        mCurrentText = (TextView) findViewById(R.id.current_title);
        mCurrentPoints = (TextView) findViewById(R.id.current_points);

        Bitmap currentIm = getRoundedShape(R.drawable.pale);

        mCurrentImage.setImageBitmap(currentIm);
        mCurrentText.setText("Plant Native Plants: ");

        mAboutButton = (ImageButton) findViewById(R.id.aboutButton);
        mAboutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });

        mProfileButton = (ImageButton) findViewById(R.id.profileButton);
        mProfileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                 startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });


        mActivityButton = (ImageButton)findViewById(R.id.activityButton);
        mActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LevelScreen.class);
                startActivity(intent);
            }
        });

        mDashButton = (ImageButton) findViewById(R.id.dashButton);
        mDashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                /*
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

                if(fragment == null) {
                    fragment = new BlankFragment();
                    fragmentManager.beginTransaction().add(R.id.fragment_container,fragment).commit();

                }
                */
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
