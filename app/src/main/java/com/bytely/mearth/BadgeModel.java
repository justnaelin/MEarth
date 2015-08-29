package com.bytely.mearth;

import android.graphics.Bitmap;

/**
 * Created by naelinaquino on 6/27/15.
 */

// A model how what each badge should contain
public class BadgeModel {
    private Bitmap mBadgeIcon;
    private String mBadgeName;
    private Boolean mIsComplete;

    public BadgeModel(String badgeName, Bitmap badgeIcon, Boolean isComplete) {
        mBadgeName = badgeName;
        mBadgeIcon = badgeIcon;
        mIsComplete = isComplete;
    }

    // Accessor methods
    public Bitmap getBadgeIcon() { return mBadgeIcon; }

    public String getBadgeName() { return mBadgeName; }

    public Boolean getIsComplete() { return mIsComplete; }

    // Mutator methods
    public void setBadgeIcon(Bitmap badgeIcon) { mBadgeIcon = badgeIcon; }

    public void setBadgeName(String badgeName) { mBadgeName = badgeName; }

    public void setIsComplete(Boolean isComplete) { mIsComplete = isComplete; }

}
