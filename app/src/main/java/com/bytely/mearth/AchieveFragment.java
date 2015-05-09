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

        int userPoints;
        userPoints = DashboardTasks.getInstance(getActivity()).getPoints();
        if(userPoints >= 2100)
        {
            ImageView badge_one = (ImageView) view.findViewById(R.id.badge_one);
            badge_one.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.image1));
        }
        if(userPoints >= 4200)
        {
            ImageView badge_two = (ImageView) view.findViewById(R.id.badge_two);
            badge_two.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award2));
        }
        if(userPoints >= 6300)
        {
            ImageView badge_three = (ImageView) view.findViewById(R.id.badge_three);
            badge_three.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award3));
        }
        if(userPoints >= 8400)
        {
            ImageView badge_four = (ImageView) view.findViewById(R.id.badge_four);
            badge_four.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award4));
        }
        if(userPoints >= 10500)
        {
            ImageView badge_five = (ImageView) view.findViewById(R.id.badge_five);
            badge_five.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award5));
        }
        if(userPoints >= 12600)
        {
            ImageView badge_six = (ImageView) view.findViewById(R.id.badge_six);
            badge_six.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award6));
        }
        if(userPoints >= 14700)
        {
            ImageView badge_seven = (ImageView) view.findViewById(R.id.badge_seven);
            badge_seven.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award7));
        }
        if(userPoints >= 16800)
        {
            ImageView badge_eight = (ImageView) view.findViewById(R.id.badge_eight);
            badge_eight.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award8));
        }
        if(userPoints >= 31800)
        {
            ImageView badge_nine = (ImageView) view.findViewById(R.id.badge_nine);
            badge_nine.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award9));
        }

        return view;
    }

}
