package com.bytely.mearth;


import android.app.Activity;
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
public class BadgeFragment extends Fragment {
    private BadgeModel[] mBadgeList; // Creates a list to hold all the badges
    private BadgeAdapter mBadgeAdapter; // TODO: Implement with badge system
    private RecyclerView mRecyclerView; // Badges will use a recycler view list
    private RecyclerView.LayoutManager mLayoutManager; //
    private Communicator mHostCommunicator; // Gives us access to the communicator
    private final android.support.v4.app.FragmentManager fragmentManager = getChildFragmentManager(); // Allows nested fragments


    public BadgeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHostCommunicator = (Communicator) getActivity();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_achieve, container, false);

        // Accesses our list of badges
        // mBadgeList = mHostCommunicator.getBadgeArray();

        // Inflates the badges in our recycler view
        // mRecyclerView = (RecyclerView) view.findViewById(R.id.badge_list);
        List<BadgeModel> badgeModelList = new ArrayList<>(Arrays.asList(mBadgeList));

        // Gets the context of the badge adapter
        mBadgeAdapter = new BadgeAdapter(getActivity(), badgeModelList);
        mRecyclerView.setAdapter(mBadgeAdapter);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mHostCommunicator = (Communicator) activity;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mHostCommunicator = (Communicator) getActivity();
        mHostCommunicator.updateActionBar();

    }

}
