package com.development.sam.inclass03;

/** Assignment - 3
Student.java
Samatha Downing
Sai Manohar Yerra **/

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sam on 5/31/16.
 */
public class Student implements Parcelable{
    String name;
    String email;
    String programmingLanguage;

    public Student(String name, String email, String programmingLanguage) {
        this.name = name;
        this.email = email;
        this.programmingLanguage = programmingLanguage;
    }

    protected Student(Parcel in) {
        name = in.readString();
        email = in.readString();
        programmingLanguage = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(programmingLanguage);
    }
}
