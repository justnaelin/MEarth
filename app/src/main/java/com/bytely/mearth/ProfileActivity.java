package com.bytely.mearth;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.logging.Level;


public class ProfileActivity extends ActionBarActivity {

    private ImageButton mAboutButton;
    private ImageButton mDashButton;
    private ImageButton mLevelButton;
    private ImageButton mAchieveButton;
    private ImageButton mCurrentButton;
    private ImageButton mGoalsButton;
    private ImageButton mPicsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        mAboutButton = (ImageButton) findViewById(R.id.aboutButton);
        mAboutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ProfileActivity.this, AboutActivity.class));
            }
        });

        mDashButton = (ImageButton) findViewById(R.id.dashButton);
        mDashButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });

        mLevelButton = (ImageButton) findViewById(R.id.levelButton);
        mLevelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ProfileActivity.this, LevelScreen.class));
            }
        });

        /*
        mAchievementButton = (Button) findViewById(R.id.sample_fragment);
        mAchievementButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.achievement_fragment);
                FragmentTransaction transaction = fragmentManager.beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);

// Commit the transaction
                transaction.commit();

                  if(fragment == null) {
                    fragment = new BlankFragment();
                    fragmentManager.beginTransaction().add(R.id.achievement_fragment).commit();
                }

            }
        }); */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
