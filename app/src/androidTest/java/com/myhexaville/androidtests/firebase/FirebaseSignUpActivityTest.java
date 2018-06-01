package com.myhexaville.androidtests.firebase;

import android.Manifest;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.firebase.auth.FirebaseAuth;
import com.myhexaville.androidtests.R;
import com.myhexaville.androidtests.chat.ChatActivity;
import com.myhexaville.androidtests.util.EspressoIdlingResource;
import com.myhexaville.androidtests.util.LogHelper;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.linkedin.android.testbutler.TestButler.verifyAnimationsDisabled;
import static com.myhexaville.androidtests.common.Constants.EMAIL;
import static com.myhexaville.androidtests.common.Constants.PASSWORD;

@RunWith(AndroidJUnit4.class)
@LargeTest
// Tests Firebase sign up with email using Idling Resourses
public class FirebaseSignUpActivityTest {
    private final static String TAG = "LEE: <" + FirebaseSignUpActivityTest.class.getSimpleName() + ">";

    @Rule
    public GrantPermissionRule permissionRule =
            GrantPermissionRule.grant(Manifest.permission.SET_ANIMATION_SCALE);

    @Rule
    public ActivityTestRule<FirebaseSignUpActivity> activityRule =
            new ActivityTestRule<>(FirebaseSignUpActivity.class);

    @Before
    public void setUp() {
//        verifyAnimationsDisabled(activityRule.getActivity());
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    @Test
    public void signUp_AccountDidntExist_Success() {
        LogHelper.v(TAG, "TEST signUp_AccountDidntExist_Success");
        presetAccountDidntExist();

        onView(withId(R.id.sign_up_button)).perform(click());
        onView(withId(R.id.sign_up_button)).check(matches(withText("Success")));
    }

    @Test
    public void signUp_AccountExisted_Fail() {
        LogHelper.v(TAG, "TEST signUp_AccountExisted_Fail");
        presetAccountExists();

        onView(withId(R.id.sign_up_button)).perform(click());
        onView(withId(R.id.sign_up_button)).check(matches(withText("Failed")));
    }

    private void presetAccountDidntExist() {
        LogHelper.v(TAG, "presetAccountDidntExist");
        EspressoIdlingResource.increment();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(EMAIL, PASSWORD)
                .addOnCompleteListener(task -> {
                    LogHelper.v(TAG, "fire on complete listener");
                    if (!task.isSuccessful()) {
                        FirebaseAuth.getInstance()
                                .signInWithEmailAndPassword(EMAIL, PASSWORD)
                                .addOnCompleteListener(runnable -> {
                                    FirebaseAuth.getInstance().getCurrentUser().delete()
                                            .addOnCompleteListener(task1 -> {
                                                EspressoIdlingResource.decrement();
                                            });
                                });
                    } else {
                        FirebaseAuth.getInstance().getCurrentUser().delete()
                                .addOnCompleteListener(t -> EspressoIdlingResource.decrement());
                    }
                });
    }

    private void presetAccountExists() {
        LogHelper.v(TAG, "presetAccountExists");
        EspressoIdlingResource.increment();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(EMAIL, PASSWORD)
                .addOnCompleteListener(task -> EspressoIdlingResource.decrement());
    }
}
