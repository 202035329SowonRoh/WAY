package com.example.way;

import android.widget.ImageView;

import io.realm.RealmObject;

public class History extends RealmObject {

    //    ImageView flag;
//    String country;
    String title;
    String description;
    long createdTime;

//    public ImageView getFlag() {
//        return flag;
//    }
//
//    public void setFlag(ImageView flag) {
//        this.flag = flag;
//    }

//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}
