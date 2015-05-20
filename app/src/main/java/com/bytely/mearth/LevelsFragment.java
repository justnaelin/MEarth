package com.bytely.mearth;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LevelsFragment extends Fragment {

    private CardView mLevelOneCard;
    private CardView mLevelTwoCard;
    private CardView mLevelThreeCard;

    private ImageView mLevelOneImage;
    private TextView mLevelOneTitle;

    private ImageView mLevelTwoImage;
    private TextView mLevelTwoTitle;

    private ImageView mLevelThreeImage;
    private TextView mLevelThreeTitle;

    private int mUserPoints;

    private Communicator comm;


    public LevelsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comm = (Communicator) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_levels, container, false);
        mLevelOneCard = (CardView) view.findViewById(R.id.level_one);
        mLevelTwoCard = (CardView) view.findViewById(R.id.level_two);
        mLevelThreeCard = (CardView) view.findViewById(R.id.level_three);




        // Get user's total points
        mUserPoints = DashboardTasks.getInstance(getActivity()).getPoints();

        // Set mLevelTwoCard & mLevelThreeCard default visibility to INVISIBLE
        mLevelTwoCard.setVisibility(View.INVISIBLE);
        mLevelThreeCard.setVisibility(View.INVISIBLE);

        // Unlock other levels
        if(mUserPoints >= 1100) {
            mLevelTwoCard.setVisibility(View.VISIBLE);
        }
        if(mUserPoints >= 4100) {
            mLevelThreeCard.setVisibility(View.VISIBLE);
        }

        comm = (Communicator) getActivity();

        mLevelOneImage = (ImageView) view.findViewById(R.id.level_one_image);
        mLevelOneTitle = (TextView) view.findViewById(R.id.level_one_title);
        mLevelTwoImage = (ImageView) view.findViewById(R.id.level_two_image);
        mLevelTwoTitle =(TextView) view.findViewById(R.id.level_two_title);
        mLevelThreeImage = (ImageView) view.findViewById(R.id.level_three_image);
        mLevelThreeTitle = (TextView) view.findViewById(R.id.level_three_title);

        Bitmap circleIconOne = FormatIcon.getRoundedShape(getActivity(), R.drawable.individual_logo);

        Bitmap circleIconTwo = FormatIcon.getRoundedShape(getActivity(), R.drawable.family_logo);

        Bitmap circleIconThree = FormatIcon.getRoundedShape(getActivity(), R.drawable.community_logo);

        mLevelOneImage.setImageBitmap(circleIconOne);
        mLevelOneTitle.setText(R.string.level_one);

        mLevelTwoImage.setImageBitmap(circleIconTwo);
        mLevelTwoTitle.setText(R.string.level_two);

        mLevelThreeImage.setImageBitmap(circleIconThree);
        mLevelThreeTitle.setText(R.string.level_three);

        mLevelOneCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               comm.runLevelOne();
            }
        });

        mLevelTwoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comm.runLevelTwo();
            }
        });

        mLevelThreeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comm.runLevelThree();
            }
        });

        return view;
    }
}
