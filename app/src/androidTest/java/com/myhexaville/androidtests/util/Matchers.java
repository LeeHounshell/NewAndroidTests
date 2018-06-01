package com.myhexaville.androidtests.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.v4.util.Preconditions.checkArgument;
import static android.text.TextUtils.isEmpty;
import static org.hamcrest.core.AllOf.allOf;

public class Matchers {
    private final static String TAG = "LEE: <" + Matchers.class.getSimpleName() + ">";

    public static Matcher<View> withItemText(final String itemText) {
        LogHelper.v(TAG, "withItemText");
        checkArgument(!isEmpty(itemText), "itemText cannot be null or empty");
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View item) {
                LogHelper.v(TAG, "matchesSafely text");
                return allOf(
                        isDescendantOfA(isAssignableFrom(RecyclerView.class)),
                        withText(itemText)).matches(item);
            }

            @Override
            public void describeTo(Description description) {
                LogHelper.v(TAG, "describeTo text");
                description.appendText("is isDescendantOfA RV with text " + itemText);
            }
        };
    }

    public static Matcher<View> hasDrawable() {
        LogHelper.v(TAG, "hasDrawable");
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                LogHelper.v(TAG, "describeTo drawable");
            }

            @Override
            protected boolean matchesSafely(View item) {
                LogHelper.v(TAG, "matchesSafely drawable");
                ImageView imageView = (ImageView) item;
                return imageView.getDrawable() != null;
            }
        };
    }

    public static Matcher<View> withTag(String tag) {
        LogHelper.v(TAG, "withTag");
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                LogHelper.v(TAG, "describeTo tag");
            }

            @Override
            protected boolean matchesSafely(View item) {
                LogHelper.v(TAG, "matchesSafely tag");
                Object t = item.getTag();
                if (t != null && t instanceof String) {
                    return tag.equals((String) t);
                } else {
                    return false;
                }
            }
        };


    }

}
