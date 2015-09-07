package com.bytely.mearth;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by yaya on 5/5/15.
 *
 * This is an abstract class that specifies the image processing for the gallery grid view.
 * The image adapter sets a custom adapter so all images are displayed
 * We inherit from BaseAdapter
 */
public class ImageAdapter extends BaseAdapter {

    //global context allows access to application-specific resources and classes.
    private Context mContext;
    public int lengthArray;
    ArrayList<String> images_file_path = new ArrayList<String>();
    ArrayList<Bitmap> bitmap_images = new ArrayList<Bitmap>();
    File[] listFile;
    Bitmap myBitmap;

    public ImageAdapter (Context c){
        mContext = c;
    }

    //constructor
    public ImageAdapter(Context c, ArrayList<Bitmap> bitmaps) {
        mContext = c;
        bitmap_images = bitmaps;

    }

    //When the image adapter is created an arraylis of bitmaps and arraylis of file;paths is send.
    //This allows us to see what image was selected.
    public ImageAdapter(Context c, ArrayList<Bitmap> bitmaps, ArrayList<String> filePaths) {
        //Initializing the variables
        mContext = c;
        bitmap_images = bitmaps;
        images_file_path = filePaths;
    }

    @Override
    public int getCount() {
        //number of thumbnails
        //return mThumbIds.length;
        return images_file_path.size();
    }

    @Override
    public String getItem(int position) {
        //returns the actual object at the specific position in the adapter
        // return mThumbIds[position];
        return  images_file_path.get(position);
    }

    public ArrayList<Bitmap> getItems(){
        return bitmap_images;
    }

    @Override    //returns the row id of the item(not needed for this gridview
    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item added by the Adapter
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public View getView(int position, View convertView, ViewGroup parent) {

        //when this is called a new image is passed in
        ImageView imageView;

        //if the convertView wasn't created it will then create it
        if (convertView == null) {
            Display display = ((AppCompatActivity)mContext).getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x / 3;
            int height = width;
            Resources r = Resources.getSystem();
            float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    60, r.getDisplayMetrics());
            // if it's not recycled, initialize some attributes
            //instantiate and configure with specific properties
            imageView = new ImageView(mContext);
            //this also crops and makes sure the dimensions are appropriate for the view
            imageView.setLayoutParams(new GridView.LayoutParams(width, height));//the size of each icon
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//crops toward the center if necessary
            imageView.setPadding(1, 1, 1, 1);//space between them
        }
        else {
            //once created we recycle the imageView
            imageView = (ImageView) convertView;
        }



        //passes the position of the thumbnail[number]
        //imageView.setImageBitmap(mThumbIds[position]);

        //setting image in gridview using the arralist of type bitmaps
        imageView.setImageBitmap(bitmap_images.get(position));
        //  imageView.setImageResource(mThumbIds[position]);


        return imageView;
    }

}
