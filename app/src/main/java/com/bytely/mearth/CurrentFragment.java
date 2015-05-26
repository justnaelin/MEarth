package com.bytely.mearth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentFragment extends Fragment {
    private ArrayList<TaskModel> mTaskList;
    private CurrentTaskAdapter mCurrentTaskAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView mTextView;

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
        mRecyclerView.setFocusable(false);

        mTextView = (TextView) view.findViewById(R.id.current_task_textview);

        if(mTaskList.size() != 0) {
            mTextView.setVisibility(View.GONE);
        } else {
            mTextView.setText(R.string.current_fragment_no_tasks);
        }

        return view;
    }
}
