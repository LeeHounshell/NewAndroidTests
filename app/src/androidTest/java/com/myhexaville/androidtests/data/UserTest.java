package com.myhexaville.androidtests.data;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import com.myhexaville.androidtests.util.LogHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class UserTest {
    private final static String TAG = "LEE: <" + UserTest.class.getSimpleName() + ">";

    private static final long SAMPLE_BIRTH_DATE = 663186154;
    private static final String JOSH = "Josh";
    private static final String PITZ = "Pitz";
    private static final String BRAZIL = "Brazil";
    private static final String SAO_PAULO = "Sao Paulo";


    @Test
    public void testCorrectUserParcelableImplementation() {
        LogHelper.v(TAG, "TEST testCorrectUserParcelableImplementation");
        User user = new User(JOSH, PITZ, SAMPLE_BIRTH_DATE, BRAZIL, SAO_PAULO);

        Parcel parcel = Parcel.obtain();
        user.writeToParcel(parcel, user.describeContents());
        parcel.setDataPosition(0);

        User createdFromParcel = User.CREATOR.createFromParcel(parcel);

        assertNoFieldsAreNull(createdFromParcel);

        assertThat(createdFromParcel.getFirstName(), is(JOSH));
        assertThat(createdFromParcel.getLastName(), is(PITZ));
        assertThat(createdFromParcel.getBirthDate(), is(SAMPLE_BIRTH_DATE));
        assertThat(createdFromParcel.getCountry(), is(BRAZIL));
        assertThat(createdFromParcel.getCity(), is(SAO_PAULO));
    }

    private void assertNoFieldsAreNull(Object user) {
        LogHelper.v(TAG, "assertNoFieldsAreNull");
        List<Field> allFields = extractAllFields(user);

        for (Field field : allFields) {
            try {
                field.setAccessible(true);
                Object o = field.get(user);
                assertNotNull(field.getName() + " is null", o);
            } catch (IllegalAccessException e) {
                fail();
            }
        }
    }

    /*
    * Extract all fields including all parent fields using reflection
    * */
    private List<Field> extractAllFields(Object o) {
        LogHelper.v(TAG, "extractAllFields");
        ArrayList<Field> result = new ArrayList<>();

        Field[] declaredFields = o.getClass().getDeclaredFields();
        Collections.addAll(result, declaredFields);

        Class superClass = o.getClass().getSuperclass();
        // Check if reached Object parent (all classes have Object class as their top parent by default)
        while (superClass != null && !superClass.toString().equals("class java.lang.Object")) {
            Collections.addAll(result, superClass.getDeclaredFields());
            superClass = superClass.getSuperclass();
        }

        return result;
    }
}
