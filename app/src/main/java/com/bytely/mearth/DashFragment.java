package com.bytely.mearth;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * A simple {@link Fragment} subclass.
 * <p/>
 * public class YoutubeFragment extends Fragment implements
 * YouTubePlayer.OnInitializedListener
 */
public class DashFragment extends Fragment {
    private Communicator comm;
    private WebView mWebView;


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


        mWebView = (WebView) view.findViewById(R.id.webView);

        String url = "http://mearthcarmel.org/special-community-events/";
        if(mWebView != null){
            mWebView.loadUrl(url);
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
        }
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
