package com.bytely.mearth;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by anitagarcia on 4/28/15.
 * This will generate Hash Key tobe used
 * for Facebook log in.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        printHashKey();
    }

    public void printHashKey(){

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.bytely.mearth",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

    }
}
