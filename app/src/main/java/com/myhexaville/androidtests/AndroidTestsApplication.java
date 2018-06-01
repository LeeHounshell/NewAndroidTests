package com.myhexaville.androidtests;

import android.app.Application;
import android.util.Log;

import com.bumptech.glide.request.target.ViewTarget;
import com.google.firebase.FirebaseApp;

public class AndroidTestsApplication extends Application {
    private final static String TAG = "LEE: <" + AndroidTestsApplication.class.getSimpleName() + ">";

    private static boolean tagSet;

    @Override
    public void onCreate() {
        Log.v(TAG, "onCreate");
        super.onCreate();
        FirebaseApp.initializeApp(this);
        if (! tagSet) {
            ViewTarget.setTagId(R.id.glide_tag);
            tagSet = true;
        }
    }
}
