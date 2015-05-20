package com.bytely.mearth;
// FYI to get profile picture, see video @ 12:30

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareButtonBase;
import com.facebook.share.widget.ShareDialog;

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
        //  -- FACEBOOK LOGIN  --
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();
        // Will handel user name change.
        mTokenTracker = new AccessTokenTracker()

        {
            @Override

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
        mProfileTracker.startTracking();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ShareDialog dialog = new ShareDialog(getActivity());
        comm = (Communicator) getActivity();
        // -- Social Media Buttons --
        // Facebook Button
        mFbButton = (ImageButton) view.findViewById(R.id.fbButton);
        mFbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.facebook.com/MEarthCarmel";
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });
        // Youtube Button
        mYoutubeButton = (ImageButton) view.findViewById(R.id.youtubeButton);
        mYoutubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/watch?v=VrCffTcfwoo";
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });
        // Instagram Button
        mInstagramButton = (ImageButton) view.findViewById(R.id.instagramButton);
        mInstagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://instagram.com/mearthcarmel";
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });


        /*
        mShareButton = (ShareButton) view.findViewById(R.id.share_button);
        // mShareButton.setShareContent(content);
        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Share Image to Facebook
                Bitmap image = BitmapFactory.decodeResource(getResources(),R.drawable.lightbulb);
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(image)
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();
                ShareDialog.show(getActivity(), content);


            }


        });
        */
        ShareButton shareButton = (ShareButton)view.findViewById(R.id.share_button);

        // Share Image to Facebook
        Bitmap image = BitmapFactory.decodeResource(getResources(),R.drawable.lightbulb);
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        shareButton.setShareContent(content);

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
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("users_friends");//only ask persion if needed
        loginButton.setFragment(this);
        loginButton.registerCallback(mCallbackManager, mCallback);


    }

    @Override
    public void onResume()
    {
        super.onResume();
        Profile profile=Profile.getCurrentProfile();
        displayWelcomeMessage(profile);
    }

    @Override
    public void onStop(){
        super.onStop();
        mTokenTracker.stopTracking();
        mProfileTracker.startTracking();

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data );
    }

}
