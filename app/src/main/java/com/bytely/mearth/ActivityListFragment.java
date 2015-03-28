package com.bytely.mearth;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityListFragment extends Fragment {
    /*
    private ImageView mActivityOneImage;
    private TextView mActivityOneTitle;
    private ImageView mActivityTwoImage;
    private TextView mActivityTwoTitle;
    private ImageView mActivityThreeImage;
    private TextView mActivityThreeTitle;
    private ImageView mActivityFourImage;
    private TextView mActivityFourTitle;
    */

    private ActivityModel[] mActivityList;
    private ActivityListAdapter mActivityListAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Communicator comm;


    public ActivityListFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public ActivityListFragment(ActivityModel[] mActivityList) {
        this.mActivityList = mActivityList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comm = (Communicator) getActivity();

        mActivityList = comm.getActivityArray();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activity_list, container, false);

        /*
        mActivityOneImage = (ImageView) view.findViewById(R.id.activity_one_image);
        mActivityOneTitle = (TextView) view.findViewById(R.id.activity_one_title);
        mActivityTwoImage = (ImageView) view.findViewById(R.id.activity_two_image);
        mActivityTwoTitle =(TextView) view.findViewById(R.id.activity_two_title);
        mActivityThreeImage = (ImageView) view.findViewById(R.id.activity_three_image);
        mActivityThreeTitle = (TextView) view.findViewById(R.id.activity_three_title);
        mActivityFourImage = (ImageView) view.findViewById(R.id.activity_four_image);
        mActivityFourTitle = (TextView) view.findViewById(R.id.activity_four_title);

        mActivityOneImage.setImageBitmap(mActivityList[0].getActivityIcon());
        mActivityTwoImage.setImageBitmap(mActivityList[1].getActivityIcon());
        mActivityThreeImage.setImageBitmap(mActivityList[2].getActivityIcon());
        mActivityFourImage.setImageBitmap(mActivityList[3].getActivityIcon());

        mActivityOneTitle.setText(mActivityList[0].getActivityName());
        mActivityTwoTitle.setText(mActivityList[1].getActivityName());
        mActivityThreeTitle.setText(mActivityList[2].getActivityName());
        mActivityFourTitle.setText(mActivityList[3].getActivityName());
        */


        mRecyclerView = (RecyclerView) view.findViewById(R.id.activity_list);
        List<ActivityModel> activityModelList = new ArrayList<>(Arrays.asList(mActivityList));

        mActivityListAdapter = new ActivityListAdapter(getActivity(), activityModelList);
        mRecyclerView.setAdapter(mActivityListAdapter);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        comm = (Communicator) getActivity();
        comm.updateActionBar();

    }
}
