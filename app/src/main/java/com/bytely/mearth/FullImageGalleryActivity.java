package com.bytely.mearth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by yaya on 5/10/15.
 */
public class FullImageGalleryActivity extends Activity{

    public FullImageGalleryActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullimage_activity);

        // get intent data
       Intent i = getIntent();

        // Selected image id passed by Extras
        int position = i.getExtras().getInt("id");
        ImageAdapter imageAdapter = new ImageAdapter(this);

       ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
       imageView.setImageResource(imageAdapter.mThumbIds[position]);
    }
}
