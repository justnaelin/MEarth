package com.bytely.mearth;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
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
 * This is an abstract class that specifies the image procesing for the gallery grid view.
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

    @Override
    public int getCount() {
        //number of thumbnails
        //return mThumbIds.length;
        return bitmap_images.size();
    }

    @Override
    public Bitmap getItem(int position) {
        //returns the actual object at the specific position in the adapter
        // return mThumbIds[position];
        return  bitmap_images.get(position);
    }

    public ArrayList<Bitmap> getItems(){
        return bitmap_images;
    }

    @Override    //returns the row id of the item(not needed for this gridview
    public long getItemId(int position) {
        return 0;
    }



    // create a new ImageView for each item added by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        //when this is called a new image is passed in
        ImageView imageView;

        Log.i("Tag", "fail");
//        myBitmap = BitmapFactory.decodeFile(images_file_path.get(0));


        //myImage.setImageBitmap(myBitmap);

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            //instantiate and configure with specific properties
            imageView = new ImageView(mContext);
            //this also crops and makes sure the dimensions are appropriate for the view
            imageView.setLayoutParams(new GridView.LayoutParams(250, 250));//the size of each icon
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//crops toward the center if necessary
            imageView.setPadding(4, 4, 4, 4);//space between them
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
    // references to our images in an array an integer
    /*public Integer[] mThumbIds = {
            R.drawable.recycle, R.drawable.about_icon,
            R.drawable.acti_icon, R.drawable.bullseye,
            R.drawable.camera, R.drawable.eco_car,
            R.drawable.eco_cleaning, R.drawable.eco_stuff,
            R.drawable.eco_fuel, R.drawable.face_icon,
            R.drawable.family_logo, R.drawable.globe,
            R.drawable.done, R.drawable.eco_fuel,
            R.drawable.recycle, R.drawable.mearth,
            R.drawable.individual_logo, R.drawable.pale,
    };*/
    // references to our images in an array
    //public Bitmap[] mThumbIds = {
    //myBitmap, myBitmap};

}
