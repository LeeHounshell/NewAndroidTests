package com.myhexaville.androidtests.image_picker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.myhexaville.androidtests.R;
import com.myhexaville.smartimagepicker.ImagePicker;

import static android.view.View.GONE;
import static com.myhexaville.androidtests.common.Constants.IMAGE_URL;
import static com.myhexaville.androidtests.common.Constants.TAG_IMAGE_URL;

public class ImagePickerActivity extends AppCompatActivity {
    private final static String TAG = "LEE: <" + ImagePickerActivity.class.getSimpleName() + ">";

    private ImagePicker imagePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView image = findViewById(R.id.image);
        ImageView person = findViewById(R.id.camera);
        ImageView secondImage = findViewById(R.id.second_image);

        imagePicker = new ImagePicker(this, null,
                imageUri -> { // on image picked
                    Log.v(TAG, "image picked");
                    image.setImageURI(imageUri);
                    person.setVisibility(GONE);
                });

        secondImage.setTag(IMAGE_URL);
        Glide.with(this).load(IMAGE_URL).into(secondImage);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v(TAG, "onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.handleActivityResult(resultCode, requestCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.v(TAG, "onRequestPermissionsResult");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imagePicker.handlePermission(requestCode, grantResults);
    }

    public void pickImage(View view) {
        Log.v(TAG, "pickImage");
        imagePicker.choosePicture(true);
    }
}
