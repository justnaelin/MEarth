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

        ImageView badge_one = (ImageView) view.findViewById(R.id.badge_one);
        badge_one.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.image1));

        ImageView badge_two = (ImageView) view.findViewById(R.id.badge_one);
        badge_two.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award2));

        ImageView badge_three = (ImageView) view.findViewById(R.id.badge_one);
        badge_three.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award3));

        ImageView badge_four = (ImageView) view.findViewById(R.id.badge_one);
        badge_four.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award4));

        ImageView badge_five = (ImageView) view.findViewById(R.id.badge_one);
        badge_five.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award5));

        ImageView badge_six = (ImageView) view.findViewById(R.id.badge_one);
        badge_six.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award6));

        ImageView badge_seven = (ImageView) view.findViewById(R.id.badge_one);
        badge_seven.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award7));

        ImageView badge_eight = (ImageView) view.findViewById(R.id.badge_one);
        badge_eight.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award8));

        ImageView badge_nine = (ImageView) view.findViewById(R.id.badge_one);
        badge_nine.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award9));


        return view;
    }


}
