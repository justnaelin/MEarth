package com.bytely.mearth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.FacebookSdk;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {
    private Communicator comm;

    public AboutFragment() {
        // Required empty public constructor
    }

    // Facebook Code to Log in (Temporarily Placed)

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        comm = (Communicator) getActivity();

        return view;
    }


}
