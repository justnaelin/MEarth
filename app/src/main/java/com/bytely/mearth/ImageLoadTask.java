package com.bytely.mearth;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * Created by juice on 7/11/15.
 */
public class ImageLoadTask extends AsyncTask<File, Void, Bitmap> {
    private WeakReference<ImageView> imageViewReference;

    public ImageLoadTask(ImageView imageView) {
        imageViewReference = new WeakReference<ImageView>(imageView);
    }

    @Override
    protected Bitmap doInBackground(File... params) {
        return BitmapFactory.decodeFile(params[0].getAbsolutePath());
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap != null && imageViewReference != null) {
            ImageView imageView = imageViewReference.get();
            if(imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
