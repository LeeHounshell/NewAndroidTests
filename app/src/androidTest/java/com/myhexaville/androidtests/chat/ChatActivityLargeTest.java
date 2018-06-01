package com.myhexaville.androidtests.chat;

import android.Manifest;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;

import com.myhexaville.androidtests.R;
import com.myhexaville.androidtests.util.EspressoIdlingResource;
import com.myhexaville.androidtests.util.LogHelper;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.linkedin.android.testbutler.TestButler.verifyAnimationsDisabled;
import static com.myhexaville.androidtests.util.Matchers.withItemText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ChatActivityLargeTest {
    private final static String TAG = "LEE: <" + ChatActivityLargeTest.class.getSimpleName() + ">";

    private static final String MESSAGE = "Some message";

    @Rule
    public GrantPermissionRule permissionRule1 =
            GrantPermissionRule.grant(Manifest.permission.CAMERA);

    @Rule
    public GrantPermissionRule permissionRule2 =
            GrantPermissionRule.grant(Manifest.permission.SET_ANIMATION_SCALE);

    @Rule
    public ActivityTestRule<ChatActivity> activityRule =
            new ActivityTestRule<>(ChatActivity.class);

    @Before
    public void setUp() {
//        verifyAnimationsDisabled(activityRule.getActivity());
    }

    @Test
    public void sendNormalMessage_MessageAdded() {
        LogHelper.v(TAG, "TEST sendNormalMessage_MessageAdded");
        onView(withId(R.id.message_input)).perform(typeText(MESSAGE), closeSoftKeyboard());
        onView(withId(R.id.send_button)).perform(click());
        onView(withId(R.id.list)).perform(scrollTo(hasDescendant(withText(MESSAGE))));
        onView(withItemText(MESSAGE)).check(matches(isDisplayed()));
    }
}
