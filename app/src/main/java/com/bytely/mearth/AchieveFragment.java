package com.bytely.mearth;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


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
        CharSequence text = "You have earned a badge!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(getActivity(), text, duration);

        int userPoints;
        userPoints = DashboardTasks.getInstance(getActivity()).getPoints();
        if(userPoints >= 2100)
        {
            ImageView badge_one = (ImageView) view.findViewById(R.id.badge_one);
            badge_one.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.image1));
            //toast.show();
        }
        if(userPoints >= 4200)
        {
            ImageView badge_two = (ImageView) view.findViewById(R.id.badge_two);
            badge_two.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award2));
            //toast.show();
        }
        if(userPoints >= 6300)
        {
            ImageView badge_three = (ImageView) view.findViewById(R.id.badge_three);
            badge_three.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award3));
            //toast.show();
        }
        if(userPoints >= 8400)
        {
            ImageView badge_four = (ImageView) view.findViewById(R.id.badge_four);
            badge_four.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award4));
            //toast.show();
        }
        if(userPoints >= 10500)
        {
            ImageView badge_five = (ImageView) view.findViewById(R.id.badge_five);
            badge_five.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award5));
            //toast.show();
        }
        if(userPoints >= 12600)
        {
            ImageView badge_six = (ImageView) view.findViewById(R.id.badge_six);
            badge_six.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award6));
            //toast.show();
        }
        if(userPoints >= 14700)
        {
            ImageView badge_seven = (ImageView) view.findViewById(R.id.badge_seven);
            badge_seven.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award7));
            //toast.show();
        }
        if(userPoints >= 16800)
        {
            ImageView badge_eight = (ImageView) view.findViewById(R.id.badge_eight);
            badge_eight.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award8));
            //toast.show();
        }
        if(userPoints >= 31800)
        {
            ImageView badge_nine = (ImageView) view.findViewById(R.id.badge_nine);
            badge_nine.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award9));
            //toast.show();
        }

        return view;
    }

}
