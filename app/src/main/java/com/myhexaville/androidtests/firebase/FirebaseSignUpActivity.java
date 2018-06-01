package com.myhexaville.androidtests.firebase;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.myhexaville.androidtests.R;
import com.myhexaville.androidtests.util.EspressoIdlingResource;

import static com.myhexaville.androidtests.common.Constants.EMAIL;
import static com.myhexaville.androidtests.common.Constants.PASSWORD;

public class FirebaseSignUpActivity extends AppCompatActivity {
    private final static String TAG = "LEE: <" + FirebaseSignUpActivity.class.getSimpleName() + ">";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_sign_up);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void signUp(View view) {
        Log.v(TAG, "signUp");
        Button b = (Button) view;

        EspressoIdlingResource.increment();
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(EMAIL, PASSWORD)
                .addOnFailureListener(command -> b.setText("Failed"))
                .addOnCompleteListener(task -> {
                    EspressoIdlingResource.decrement();
                    if (task.isSuccessful()) {
                        b.setText("Success");
                    } else {
                        b.setText("Failed");
                    }
                });
    }

}
