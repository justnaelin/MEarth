package com.bytely.mearth;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AchieveFragment extends Fragment {

    Toast mBadgeOneToast;
    Toast mBagdeTwoToast;
    Toast mBadgeThreeToast;
    Toast mBadgeFourToast;
    Toast mBadgeFiveToast;
    Toast mBadgeSixToast;
    Toast mBadgeSevenToast;
    Toast mBadgeEightToast;
    Toast mBadgeNineToast;

    TextView mTextView;
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
        mTextView = (TextView) view.findViewById(R.id.nobadge_textview);


        int userPoints;
        userPoints = DashboardTasks.getInstance(getActivity()).getPoints();
        if(userPoints < 1000) {
           mTextView.setText(R.string.achieve_fragment_no_badges);
        }
        if(userPoints > 1000)
            mTextView.setVisibility(View.GONE);

        // Badge One
        if(userPoints >= 1000)
        {

            ImageView badge_one = (ImageView) view.findViewById(R.id.badge_one);
            badge_one.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.image1));

            // Toast
            badge_one.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){

                    mBadgeOneToast = Toast.makeText(getActivity(), "You have earned 1000 points", Toast.LENGTH_SHORT);
                    mBadgeOneToast.show();
                }
            });
        }

        // Badge Two
        if(userPoints >= 2000)
        {

            ImageView badge_two = (ImageView) view.findViewById(R.id.badge_two);
            badge_two.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award2));

            // Toast
            badge_two.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mBadgeOneToast = Toast.makeText(getActivity(), "You have earned 2000 points", Toast.LENGTH_SHORT);
                    mBadgeOneToast.show();
                }
            });
        }

        // Badge Three
        if(userPoints >= 4000)
        {

            ImageView badge_three = (ImageView) view.findViewById(R.id.badge_three);
            badge_three.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award3));

            // Toast
            badge_three.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mBadgeOneToast = Toast.makeText(getActivity(), "You have earned 4000 points", Toast.LENGTH_SHORT);
                    mBadgeOneToast.show();
                }
            });
        }
        // Badge Four
        if(userPoints >= 6000)
        {
            ImageView badge_four = (ImageView) view.findViewById(R.id.badge_four);
            badge_four.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award4));

            // Toast
            badge_four.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mBadgeOneToast = Toast.makeText(getActivity(), "You have earned 6000 points", Toast.LENGTH_SHORT);
                    mBadgeOneToast.show();
                }
            });
        }
        // Badge Five
        if(userPoints >= 8000)
        {
            ImageView badge_five = (ImageView) view.findViewById(R.id.badge_five);
            badge_five.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award5));

            // Toast
            badge_five.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mBadgeOneToast = Toast.makeText(getActivity(), "You have earned 8000 points", Toast.LENGTH_SHORT);
                    mBadgeOneToast.show();
                }
            });
        }

        // Badge Six
        if(userPoints >= 12000)
        {
            ImageView badge_six = (ImageView) view.findViewById(R.id.badge_six);
            badge_six.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award6));

            // Toast
            badge_six.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mBadgeOneToast = Toast.makeText(getActivity(), "You have earned 12000 points", Toast.LENGTH_SHORT);
                    mBadgeOneToast.show();
                }
            });
        }

        // Badge Seven
        if(userPoints >= 15000)
        {
            ImageView badge_seven = (ImageView) view.findViewById(R.id.badge_seven);
            badge_seven.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award7));

            // Toast
            badge_seven.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mBadgeOneToast = Toast.makeText(getActivity(), "You have earned 15000 points", Toast.LENGTH_SHORT);
                    mBadgeOneToast.show();
                }
            });
        }

        // Badge Eight
        if(userPoints >= 18000)
        {
            ImageView badge_eight = (ImageView) view.findViewById(R.id.badge_eight);
            badge_eight.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award8));

            // Toast
            badge_eight.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mBadgeOneToast = Toast.makeText(getActivity(), "You have earned 18000 points", Toast.LENGTH_SHORT);
                    mBadgeOneToast.show();
                }
            });
        }

        // Badge Nine
        if(userPoints >= 25000)
        {
            ImageView badge_nine = (ImageView) view.findViewById(R.id.badge_nine);
            badge_nine.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.new_award9));

            // Toast
            badge_nine.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mBadgeOneToast = Toast.makeText(getActivity(), "You have earned 25000 points", Toast.LENGTH_SHORT);
                    mBadgeOneToast.show();
                }
            });
        }
        container.removeAllViews();
        return view;
    }

}
