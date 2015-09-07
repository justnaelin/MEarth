package com.bytely.mearth;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashFragment extends Fragment {
    private Communicator comm;


    public DashFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        comm = (Communicator) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Dash", "onCreate");
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("Dash", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_dash, container, false);
        comm.hideUnderlineViews();
        comm.showUnderlineView(0);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Dash", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Dash", "onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Dash", "onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Dash", "onDestroy");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Dash", "onStop");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Dash", "onStart");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Dash", "onDestroyView");
    }
}
