package com.bytely.mearth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentFragment extends Fragment {
    private ArrayList<TaskModel> mTaskList;
    private CurrentTaskAdapter mCurrentTaskAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    public CurrentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current, container, false);

        mTaskList = DashboardTasks.getInstance(getActivity()).getTaskList();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.profile_task_list);
        mCurrentTaskAdapter = new CurrentTaskAdapter(getActivity(), mTaskList);

        mRecyclerView.setAdapter(mCurrentTaskAdapter);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        return view;
    }


}
