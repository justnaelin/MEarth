package com.bytely.mearth;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class LevelsActivity extends ActionBarActivity {
    private TextView nameText;

    private TextView descriptionText;
    private TextView pointsText;
    private ImageView circle;

    private ImageView image;
    private TaskModel activity;
    //private CardView cardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);


        nameText = (TextView)findViewById(R.id.name_text);
        //descriptionText = (TextView)findViewById(R.id.description_text);
        //pointsText = (TextView)findViewById(R.id.points_text);
        circle = (ImageView)findViewById(R.id.icon);

        //activity = new TaskModel("This is an activity", R.string.description, 20);
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.drawable.red_individual);
        Bitmap circleIcon = getRoundedShape(logo);

        circle.setImageBitmap(circleIcon);
        //nameText.setText(activity.getActivityName());
        //descriptionText.setText(activity.getActivityDescription());
        //pointsText.setText(Integer.toString(activity.getActivityPoints()));
    }


    public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        int targetWidth = 150;
        int targetHeight = 150;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight,Bitmap.Config.ARGB_8888);

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
        //cardView = (CardView) findViewById(R.id.card_view);
        nameText = (TextView) findViewById(R.id.name_text);
        image    = (ImageView)findViewById(R.id.image);

        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.drawable.splash_logo);
        //Bitmap circleIcon = getRoundedShape(logo);

        //activity = new ActivityModel("Level 1: Individual Activities", R.string.description, circleIcon, 20);

//        image.setImageBitmap(activity.getActivityIcon());
 //       nameText.setText(activity.getActivityName());

    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_levels, menu);
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
}
