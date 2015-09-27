package com.bytely.mearth;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;


public class DeletePhotoDialogFragment extends DialogFragment {
    // Values to store Intent Values
    String mFilePath;
    int position;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mFilePath = getArguments().getString("file_path");
        position = getArguments().getInt("position");
    }

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState){

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Delete Photo");
        builder.setMessage("Are you sure you would like to delete this picture? ");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               // Toast.makeText(getActivity(), "Negative button was clicked", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Gather's image to delete from Gallery Fragement
                File file = new File(mFilePath);
               // boolean deleted =
                file.delete();
                // Test Toast
                // Toast.makeText(getActivity(), "Positive button was clicked", Toast.LENGTH_SHORT).show();
                GalleryFragment.bitmap_images.remove(position);
                GalleryFragment.images_file_path.remove(position);
                Log.e(" DeleteDialog(oC) ", " " + position);
                GridView grid = GalleryFragment.gridview;
                grid.invalidateViews();

                ImageAdapter adapter = ((ImageAdapter) grid.getAdapter());
                adapter.notifyDataSetChanged();
                grid.setAdapter(adapter);

                getActivity().finish();
            }
        });
        Dialog dialog = builder.create();

        return dialog;
    }
    public static DeletePhotoDialogFragment getInstance(String filePath, int position){
        DeletePhotoDialogFragment photoDialogFragment = new DeletePhotoDialogFragment();
        Bundle args = new Bundle();
        args.putString("file_path", filePath);
        args.putInt("position", position);
        photoDialogFragment.setArguments(args);
        return photoDialogFragment;
    }

}
