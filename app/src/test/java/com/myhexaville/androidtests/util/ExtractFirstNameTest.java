package com.myhexaville.androidtests.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static com.myhexaville.androidtests.util.FirstNameExtractor.extractFirstName;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ExtractFirstNameTest {
    private final static String TAG = "LEE: <" + ExtractFirstNameTest.class.getSimpleName() + ">";

    private static String DAVE = "Dave";

    @Test
    public void extractFirstName_NullInput_ReturnEmptyString() {
        LogHelper.v(TAG, "TEST extractFirstName_NullInput_ReturnEmptyString");
        assertThat(extractFirstName(null), is(""));
    }

    @Test
    public void extractFirstName_EmptyString_ReturnEmptyString() {
        LogHelper.v(TAG, "TEST extractFirstName_EmptyString_ReturnEmptyString");
        assertThat(extractFirstName(""), is(""));
    }

    @Test
    public void extractFirstName_FullName_ReturnsCorrect() {
        LogHelper.v(TAG, "TEST extractFirstName_FullName_ReturnsCorrect");
        assertThat(extractFirstName("Dave Jones"), is(DAVE));
    }

    @Test
    public void extractFirstName_FullNameAroundWhiteSpaces_ReturnsCorrect() {
        LogHelper.v(TAG, "TEST extractFirstName_FullNameAroundWhiteSpaces_ReturnsCorrect");
        assertThat(extractFirstName("Dave Jones "), is(DAVE));
        assertThat(extractFirstName(" Dave Jones"), is(DAVE));
        assertThat(extractFirstName("Dave   Jones"), is(DAVE));
        assertThat(extractFirstName("   Dave Jones   "), is(DAVE));
        assertThat(extractFirstName(" Dave Jones  "), is(DAVE));
    }

    @Test
    public void extractFirstName_FirstName_ReturnsCorrect() {
        LogHelper.v(TAG, "TEST extractFirstName_FirstName_ReturnsCorrect");
        assertThat(extractFirstName("Dave"), is(DAVE));
    }

    @Test
    public void extractFirstName_FirstNameAroundWhiteSpaces_ReturnsCorrect() {
        LogHelper.v(TAG, "TEST extractFirstName_FirstNameAroundWhiteSpaces_ReturnsCorrect");
        assertThat(extractFirstName("Dave "), is(DAVE));
        assertThat(extractFirstName(" Dave"), is(DAVE));
        assertThat(extractFirstName(" Dave "), is(DAVE));
        assertThat(extractFirstName("  Dave   "), is(DAVE));
    }

    @Test
    public void extractFirstName_ThreeWords_ReturnsCorrect() {
        LogHelper.v(TAG, "TEST extractFirstName_ThreeWords_ReturnsCorrect");
        assertThat(extractFirstName("Dave Dean Jones"), is(DAVE));
    }

    @Test
    public void extractFirstName_ThreeWordsAroundWhiteSpaces_ReturnsCorrect() {
        LogHelper.v(TAG, "TEST extractFirstName_ThreeWordsAroundWhiteSpaces_ReturnsCorrect");
        assertThat(extractFirstName("Dave Dean Jones "), is(DAVE));
        assertThat(extractFirstName(" Dave Dean Jones"), is(DAVE));
        assertThat(extractFirstName("   Dave Dean Jones   "), is(DAVE));
        assertThat(extractFirstName("Dave   Dean   Jones"), is(DAVE));
        assertThat(extractFirstName("  Dave    Dean   Jones"), is(DAVE));
        assertThat(extractFirstName("  Dave   Dean    Jones"), is(DAVE));
    }
}
