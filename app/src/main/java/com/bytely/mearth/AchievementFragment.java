package com.bytely.mearth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AchievementFragment extends Fragment {
    private TextView txtview;

    public AchievementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_achievement, container, false);
        /*
        ArrayList<ActivityModel> completed_activites = new ArrayList<ActivityModel>();
        if(activity_card.isCompleted())
        {
            completed_activites.add(activity_card);
        }
        for(int i = 0; i < completed_activities.size(); i++) {
            // Display cards
        }

        */

        txtview = (TextView) view.findViewById(R.id.sample_fragment);

        return view;
    }


}
