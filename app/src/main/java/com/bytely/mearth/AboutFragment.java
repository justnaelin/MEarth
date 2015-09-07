package com.bytely.mearth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.share.widget.ShareButton;

//import com.facebook.UiLifecycleHelper;


/**
 * A simple {@link Fragment} subclass.
 */


public class AboutFragment extends Fragment {

    // Facebook Share Image Button
    private ShareButton mShareButton;


    // Social Media Buttons
    private ImageButton mFbButton;
    private ImageButton mInstagramButton;
    private ImageButton mYoutubeButton;

    private Communicator comm;

    private TextView mTextDetails;
    private CallbackManager mCallbackManager;
    private AccessTokenTracker mTokenTracker;
    private ProfileTracker mProfileTracker;

    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {


        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile(); // Allows access to the person's profile
            displayWelcomeMessage(profile);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };

    public AboutFragment() {
        // Required empty public constructor
    }

    // Facebook Code to Log in (Temporarily Placed)

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comm = (Communicator) getActivity();

        /*
        //  ----- FACEBOOK LOGIN  ----
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();
        // Will handel user name change.
        mTokenTracker = new AccessTokenTracker()

        {
            //@Override

            protected void onCurrentAccessTokenChanged(AccessToken old, AccessToken newToken) {

            }

        };
        // Track user profile changes
        mProfileTracker = new ProfileTracker() {

            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                displayWelcomeMessage(newProfile);
            }
        };

        // Begins tracking
        mTokenTracker.startTracking();
        mProfileTracker.startTracking();*/


    }

    //This method is called when the AboutFragment is create to have the fragment instantiate its
    //  user interface view.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        // -- Social Media Buttons --
        // Facebook Button, This launches MEarth's facebook page
        comm.hideUnderlineViews();
        comm.showUnderlineView(3);
        mFbButton = (ImageButton) view.findViewById(R.id.fbButton);
        mFbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.facebook.com/MEarthCarmel";

                //We create an intent to start a new task (launching a url)
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });
        // Youtube Button, This launches MEarth's youtube channel
        mYoutubeButton = (ImageButton) view.findViewById(R.id.youtubeButton);
        mYoutubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/watch?v=VrCffTcfwoo";

                //We create an intent to start a new task (launching a url)
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });
        // Instagram Button, this launhes MEarth's Instagram page
        mInstagramButton = (ImageButton) view.findViewById(R.id.instagramButton);
        mInstagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://instagram.com/mearthcarmel";

                //We create an intent to start a new task (launching a url)
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });



       return view;
    }

    private void displayWelcomeMessage(Profile profile){

        if (profile != null) {
            mTextDetails.setText("Welcome " + profile.getName());

        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume()
    {
        super.onResume();

    }

    @Override
    public void onStop(){
        super.onStop();


    }


    //Whenn an intent is launch it starts the OnActivity Result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

}
