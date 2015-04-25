package com.bytely.mearth;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.io.File;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PicsFragment extends Fragment {

    private ImageButton mCameraButton;
    private File imageFile;
    ArrayList<Bitmap> photoGallery = new ArrayList<>();

    public PicsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pics, container, false);
        mCameraButton = (ImageButton) view.findViewById(R.id.cameraButton);
        mCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process();
            }
        });
        return view;
    }

    public void process(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(requestCode == 0)
        {
            switch(resultCode){
                case Activity.RESULT_OK: {
                    Bitmap image = (Bitmap) data.getExtras().get("data");
                    photoGallery.add(image);
                    for(int i = 0; i < photoGallery.size(); i++){
                        Log.d(("Tag"), photoGallery.get(i).toString() + " was added! ");
                    }
                }
                break;
                case Activity.RESULT_CANCELED:
                    break;
                default:
                    break;
            }
        }

    }

}
