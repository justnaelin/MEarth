package com.bytely.mearth;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.File;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */

public class GalleryFragment extends Fragment {

    ArrayList<String> images_file_path = new ArrayList<String>();
    public static ArrayList<Bitmap> bitmap_images;

    File[] listFile;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        bitmap_images = new ArrayList<Bitmap>();

        //Toast toast = Toast.makeText(getActivity(), "image", Toast.LENGTH_SHORT);
        //toast.show();

        //Grid view images
        int iconSize=getResources().getDimensionPixelSize(android.R.dimen.app_icon_size);
        setGalleryImages();
        setBitmap_images();
        Log.d("Gallery", "Array size: " + bitmap_images.size());



     /*  Bitmap myBitmap = BitmapFactory.decodeFile(images_file_path.get(0));

        ImageView myImage = (ImageView) view.findViewById(R.id.imageView_gallery);

        ImageAdapter imageAdapter = new ImageAdapter(getActivity());

        ArrayList<Bitmap> bitmap_images = new ArrayList<Bitmap>(imageAdapter.getItems());

          myImage.setImageBitmap(imageAdapter.getItem(0)); */



        //find the gridview by id
        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        //setAdaptersets method sets a custom adapter of images

        gridview.setAdapter(new ImageAdapter(getActivity(), bitmap_images));

        //if one image is clicked the setOnItemClickListener is called
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {            // calling the ImageAdapter class

                // Sending image id to FullScreenActivity
                Intent intent = new Intent(getActivity(), FullImageGalleryActivity.class);
                // passing array index to display that image
                intent.putExtra("id", position);
                getActivity().startActivity(intent);

            }
        });
        return view;
    }


    public void setGalleryImages(){

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
            bitmap_images.add(BitmapFactory.decodeFile(images_file_path.get(i)));

        }

    }


}
