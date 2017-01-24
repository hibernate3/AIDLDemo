package com.example.aidldemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wangyuhang on 2017/1/23.
 */

public class Person implements Parcelable {
    private String name;
    private int sex;

    protected Person(Parcel in) {
        readFromParcel(in);
    }

    public Person(){}

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        //注意写入变量和读取变量的顺序应该一致 不然得不到正确的结果
        parcel.writeString(name);
        parcel.writeInt(sex);
    }

    public void readFromParcel(Parcel parcel) {
        //注意读取变量和写入变量的顺序应该一致 不然得不到正确的结果
        name = parcel.readString();
        sex = parcel.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
