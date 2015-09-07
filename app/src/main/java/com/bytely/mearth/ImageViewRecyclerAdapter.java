package com.bytely.mearth;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.util.List;

/**
 * Created by juice on 7/11/15.
 */
public class ImageViewRecyclerAdapter extends RecyclerView.Adapter<ImageViewRecyclerAdapter.MyViewHolder> {
    private Context mContext;
    //private LayoutInflater mLayoutInflater;
    private List<File> mImageFiles;

    public ImageViewRecyclerAdapter(Context mContext, List<File> mImageFiles) {
        this.mContext = mContext;
        this.mImageFiles = mImageFiles;
        //mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = new ImageView(mContext);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(250, 250));

        MyViewHolder holder = new MyViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        File currentFile = mImageFiles.get(position);
        holder.imageView.setImageBitmap(BitmapFactory.decodeFile(currentFile.getAbsolutePath()));

        return;
    }

    @Override
    public int getItemCount() {
        return mImageFiles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView;
        }
    }
}
