package com.bytely.mearth;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */

public class GalleryFragment extends Fragment {

    ArrayList<String> images_file_path = new ArrayList<String>();
    File[] listFile;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        //Grid view images
        int iconSize=getResources().getDimensionPixelSize(android.R.dimen.app_icon_size);

            /*setGalleryImages();

            Bitmap myBitmap = BitmapFactory.decodeFile(images_file_path.get(0));

            ImageView myImage = (ImageView) view.findViewById(R.id.galleryImageView);

            myImage.setImageBitmap(myBitmap);

            Toast toast = Toast.makeText(getActivity(), images_file_path.get(0), Toast.LENGTH_LONG);
            toast.show();
            */

        //find the gridview by id
            GridView gridview = (GridView) view.findViewById(R.id.gridview);
            //setAdaptersets method sets a custom adapter of images
            // calling the ImageAdapter class
            gridview.setAdapter(new ImageAdapter(getActivity()));

            //if one image is clicked the setOnItemClickListener is called
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {

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


}
