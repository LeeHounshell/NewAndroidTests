package com.myhexaville.androidtests.image_picker;

import android.Manifest;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;

import com.myhexaville.androidtests.MainActivity;
import com.myhexaville.androidtests.R;
import com.myhexaville.androidtests.util.LogHelper;
import com.theartofdev.edmodo.cropper.CropImage;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.myhexaville.androidtests.common.Constants.IMAGE_URL;
import static com.myhexaville.androidtests.util.Matchers.hasDrawable;
import static com.myhexaville.androidtests.util.Matchers.withTag;
import static com.theartofdev.edmodo.cropper.CropImage.CROP_IMAGE_EXTRA_RESULT;
import static com.theartofdev.edmodo.cropper.CropImage.CROP_IMAGE_EXTRA_SOURCE;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.not;

public class ImagePickerActivityTest {
    private final static String TAG = "LEE: <" + ImagePickerActivityTest.class.getSimpleName() + ">";

    @Rule
    public GrantPermissionRule permissionRule2 =
            GrantPermissionRule.grant(Manifest.permission.SET_ANIMATION_SCALE);

    @Rule
    public IntentsTestRule<ImagePickerActivity> activityRule =
            new IntentsTestRule<>(ImagePickerActivity.class);


    @Before
    public void setUp() {
        savePickedImage();
        intending(hasAction(Intent.ACTION_CHOOSER)).respondWith(getCroppedImageResult());
    }

    @Test
    public void default_ImageHasNoDrawable(){
        LogHelper.v(TAG, "TEST default_ImageHasNoDrawable");
        onView(withId(R.id.image)).check(matches(not(hasDrawable())));
    }

    @Test
    public void pickImage_ImagePicked(){
        LogHelper.v(TAG, "TEST pickImage_ImagePicked");
        onView(withId(R.id.camera)).perform(click());
        onView(withId(R.id.image)).check(matches(hasDrawable()));
    }

    @Test
    public void correctImageShouldBeLoaded(){
        LogHelper.v(TAG, "TEST correctImageShouldBeLoaded");
        onView(withId(R.id.second_image)).check(matches(withTag(IMAGE_URL)));
    }

    private Instrumentation.ActivityResult getCroppedImageResult() {
        LogHelper.v(TAG, "getCroppedImageResult");
        Intent resultData = new Intent();
        File dir = activityRule.getActivity().getExternalCacheDir();
        File file = new File(dir.getPath(), "pickImageResult.jpeg");
        Uri uri = Uri.fromFile(file);
        resultData.putExtra(CROP_IMAGE_EXTRA_RESULT, new CropImage.ActivityResult(null, uri, null, null, null, 0, 1));
        return new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
    }

    private void savePickedImage() {
        LogHelper.v(TAG, "savePickedImage");
        Bitmap bm = BitmapFactory.decodeResource(activityRule.getActivity().getResources(), R.drawable.android);
        assertTrue(bm != null);
        File dir = activityRule.getActivity().getExternalCacheDir();
        File file = new File(dir.getPath(), "pickImageResult.jpeg");
        System.out.println(file.getAbsolutePath());
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
