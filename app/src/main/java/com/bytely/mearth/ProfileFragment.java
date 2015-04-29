package com.bytely.mearth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private Communicator comm;


    private ImageButton mAchieveButton;
    private ImageButton mCurrentButton;
    private ImageButton mGoalsButton;
    private ImageButton mPicsButton;



    public ProfileFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comm = (Communicator) getActivity();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);


        final FragmentManager fragmentManager = getChildFragmentManager();
        final Fragment achieveFragment = new AchieveFragment();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.profile_fragment_container, achieveFragment);
        fragmentTransaction.commit();

        mAchieveButton = (ImageButton) view.findViewById(R.id.achieve_button);
        mAchieveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Fragment achieveFragment = new AchieveFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.profile_fragment_container, achieveFragment);
                fragmentTransaction.addToBackStack("profile_achieve_fragment");
                fragmentTransaction.commit();
            }
        });

        mCurrentButton = (ImageButton) view.findViewById(R.id.current_button);
        mCurrentButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Fragment currentFragment = new CurrentFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.profile_fragment_container, currentFragment);
                fragmentTransaction.addToBackStack("profile_current_fragment");
                fragmentTransaction.commit();
            }
        });

        mGoalsButton = (ImageButton) view.findViewById(R.id.goals_button);
        mGoalsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Fragment goalsFragment = new GoalsFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.profile_fragment_container, goalsFragment);
                fragmentTransaction.addToBackStack("profile_goals_fragment");
                fragmentTransaction.commit();
            }
        });

        mPicsButton = (ImageButton) view.findViewById(R.id.pics_button);
        mPicsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Fragment picsFragment = new PicsFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.profile_fragment_container, picsFragment);
                fragmentTransaction.addToBackStack("profile_pics_fragment");
                fragmentTransaction.commit();
            }
        });

        return view;
    }


}
