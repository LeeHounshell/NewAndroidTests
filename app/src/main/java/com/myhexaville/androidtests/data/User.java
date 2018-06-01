package com.myhexaville.androidtests.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class User implements Parcelable {
    private final static String TAG = "LEE: <" + User.class.getSimpleName() + ">";

    private String firstName, lastName, country, city;
    private long birthDate;

    public User(String firstName, String lastName, long birthDate, String country, String city) {
        Log.v(TAG, "User");
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.country = country;
        this.city = city;
    }

    protected User(Parcel in) {
        Log.v(TAG, "User Parcel");
        firstName = in.readString();
        lastName = in.readString();
        birthDate = in.readLong();
        country = in.readString();
        city = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            Log.v(TAG, "createFromParcel");
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            Log.v(TAG, "newArray");
            return new User[size];
        }
    };

    public String getFirstName() {
        Log.v(TAG, "getFirstName");
        return firstName;
    }

    public void setFirstName(String firstName) {
        Log.v(TAG, "setFirstName");
        this.firstName = firstName;
    }

    public String getLastName() {
        Log.v(TAG, "getLastName");
        return lastName;
    }

    public void setLastName(String lastName) {
        Log.v(TAG, "setLastName");
        this.lastName = lastName;
    }

    public long getBirthDate() {
        Log.v(TAG, "getBirthDate");
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        Log.v(TAG, "setBirthDate");
        this.birthDate = birthDate;
    }

    public String getCountry() {
        Log.v(TAG, "getCountry");
        return country;
    }

    public void setCountry(String country) {
        Log.v(TAG, "setCountry");
        this.country = country;
    }

    public String getCity() {
        Log.v(TAG, "getCity");
        return city;
    }

    public void setCity(String city) {
        Log.v(TAG, "setCity");
        this.city = city;
    }

    @Override
    public int describeContents() {
        Log.v(TAG, "describeContents");
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.v(TAG, "writeToParcel");
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeLong(birthDate);
        dest.writeString(country);
        dest.writeString(city);
    }
}
