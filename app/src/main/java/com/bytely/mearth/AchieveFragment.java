package com.bytely.mearth;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AchieveFragment extends Fragment {


    public AchieveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_achieve, container, false);
        ImageView image_view = (ImageView) view.findViewById(R.id.image);

        int userPoints;
        userPoints = DashboardTasks.getInstance(getActivity()).getPoints();
        if(userPoints >= 2100)
        {
            image_view.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.image1));
        }
        if(userPoints >= 4200)
        {
            image_view.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award2));
        }
        if(userPoints >= 6300)
        {
            image_view.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award3));
        }
        if(userPoints >= 8400)
        {
            image_view.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award4));
        }
        if(userPoints >= 10500)
        {
            image_view.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award5));
        }
        if(userPoints >= 12600)
        {
            image_view.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award6));
        }
        if(userPoints >= 14700)
        {
            image_view.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award7));
        }
        if(userPoints >= 16800)
        {
            image_view.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award8));
        }
        if(userPoints >= 31800)
        {
            image_view.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award9));
        }


        return view;
    }

}
