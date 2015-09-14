package com.bytely.mearth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by yaya on 5/10/15.
 */
public class FullImageGalleryActivity extends Activity{
    /*
        // Instagram sharing button
        ImageButton mInstaShare;
        // Facebook sharing button
        ImageButton mFacebookShare;
        // Delete sharing button
     */
    ImageButton mDeleteButton;
    // Intent value from Gallery Fragment
    // position of photo gallery index
    // containing file path
    int position;
    // Intent value from Gallery Fragment
    // file_path contained inside array list
    // of index 'position'
    String file_path;
    public FullImageGalleryActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullimage_activity);

        // get intent data
       Intent i = getIntent();
        Intent deleteInent = getIntent();

        // Selected image id passed by Extras
        position = i.getExtras().getInt("id");
        file_path = i.getExtras().getString("path");
       // ImageAdapter imageAdapter = new ImageAdapter(this);

       ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
      // imageView.setImageResource(GalleryFragment.bitmap_images.get(position));
        imageView.setImageBitmap(GalleryFragment.bitmap_images.get(position));

        // Delete Button
        mDeleteButton = (ImageButton) findViewById(R.id.delete_button);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }
    public void deletePhoto(File file) {
        boolean deleted = file.delete();
    }

    public void showDialog(){
        DeletePhotoDialogFragment myAlert = DeletePhotoDialogFragment.getInstance(file_path, position);
        myAlert.show(getFragmentManager(), "My Alert");

    }
}
