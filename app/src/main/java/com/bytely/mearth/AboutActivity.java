package com.bytely.mearth;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class AboutActivity extends ActionBarActivity {
    private ImageButton mProfileButton;
    private ImageButton mDashButton;
    private ImageButton mLevelButton;
    private ImageButton mFBButton;
    private ImageButton mInstButton;
    private ImageButton mYouTButton;
    private ImageButton mWebsiButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mProfileButton = (ImageButton) findViewById(R.id.profileButton);
        mProfileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(AboutActivity.this, ProfileActivity.class));
            }
        });

        mDashButton = (ImageButton) findViewById(R.id.dashButton);
        mDashButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(AboutActivity.this, MainActivity.class));
            }
        });

        mLevelButton = (ImageButton) findViewById(R.id.levelButton);
        mLevelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(AboutActivity.this, LevelScreen.class));
            }
        });

        mFBButton = (ImageButton) findViewById(R.id.fbButton);
        mFBButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                String url = "https://www.facebook.com/MEarthCarmel";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        mInstButton = (ImageButton) findViewById(R.id.instagramButton);
        mInstButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String url = "https://instagram.com/mearthcarmel";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });



        mYouTButton = (ImageButton) findViewById(R.id.youtubeButton);
        mYouTButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String url = "https://www.youtube.com/user/MEarthVideos";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        /*
        mWebsiButton = (ImageButton) findViewById(R.id.websiButton);
        mWebsiButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String url = "http://mearthcarmel.org/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
*/



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
