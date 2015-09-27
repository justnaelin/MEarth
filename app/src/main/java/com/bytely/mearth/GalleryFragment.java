package com.bytely.mearth;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


/**
 * A simple {@link Fragment} subclass.
 */

public class GalleryFragment extends Fragment {
    public static ArrayList<String> images_file_path = new ArrayList<String>();
    public static ArrayList<Bitmap> bitmap_images;

    Intent intent;
    public static GridView gridview;
    File[] listFile;

    ImageAdapter adapter;

    Communicator hostCommunicator;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume(){
        super.onResume();
        images_file_path = hostCommunicator.getPhotoPaths();
        bitmap_images = hostCommunicator.getGalleryBitmaps();
        Log.d("GalleryFragment", "onResume()");




    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        hostCommunicator = (Communicator) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        // ** ORIGINAL ** bitmap_images = new ArrayList<Bitmap>();

        //Toast toast = Toast.makeText(getActivity(), "image", Toast.LENGTH_SHORT);
        //toast.show();

        images_file_path = hostCommunicator.getPhotoPaths();
        bitmap_images = hostCommunicator.getGalleryBitmaps();
       // bitmap_images = new ArrayList<Bitmap>();


        //Grid view images
        int iconSize = getResources().getDimensionPixelSize(android.R.dimen.app_icon_size);
         //setGalleryImages();
         //setBitmap_images();
        //Log.d("Gallery", "Array size: " + bitmap_images.size());

        //find the gridview by id
        gridview = (GridView) view.findViewById(R.id.gridview);
        //setAdaptersets method sets a custom adapter of images

        final Handler handler = new Handler() {
            @Override
            public void close() {

            }

            @Override
            public void flush() {

            }

            @Override
            public void publish(LogRecord record) {

            }
        };

        Log.d("CUSTOM", "galleryFragment");
        gridview.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 3s

                adapter = new ImageAdapter(getActivity(), bitmap_images);


                gridview.setAdapter(adapter);
            }
        }, 3000);


        //if one image is clicked the setOnItemClickListener is called
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {            // calling the ImageAdapter class

                // Sending image id to FullScreenActivity
                intent = new Intent(getActivity(), FullImageGalleryActivity.class);
                // passing array index to display that image
                intent.putExtra("id", position);
                Log.e(" Grid View(oC):Position", " " + position);
               // Log.e(" File Path(oC) ", " " + images_file_path.get(position));

                // Passing file path
               intent.putExtra("path", images_file_path.get(position));
               // Log.e(" File Path (oC) ", " " + images_file_path.get(position));
                getActivity().startActivity(intent);
            }
        });
        container.removeAllViews();

        return view;
    }

    public void setGalleryImages(){
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

    public void setBitmap_images(){

        for (int i = 0; i < images_file_path.size(); i++)
        {
            //bitmap_images.add(BitmapFactory.decodeFile(images_file_path.get(i)));
            bitmap_images.add(loadBitmap(images_file_path.get(i)));

        }
    }




    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public Bitmap loadBitmap(String bitmapFilePath) {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x / 3;
        int height = width;

        Resources r = Resources.getSystem();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                60, r.getDisplayMetrics());

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

    public void updateAdapter() {
        images_file_path = hostCommunicator.getPhotoPaths();
        bitmap_images = hostCommunicator.getGalleryBitmaps();
        adapter.notifyDataSetChanged();
        gridview.setAdapter(adapter);
        Log.d("updateAdapter", "Updated");

        /*
        if(gridview != null) {
            gridview.setAdapter(new ImageAdapter(getActivity(), bitmap_images));
            gridview.invalidateViews();
        } */
    }

}
