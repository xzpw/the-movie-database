package com.example.whattowatch.model.mymodel;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MyMovieModel {
    @PrimaryKey@NonNull
    private  String mTitle;
    private  String mOverview;
    private  String mDate;
    private  Float mRate;
    private  String mImageLink;
    private Integer mId;
    private  String type;



    public MyMovieModel(String title, String overview, String date, Float rate, String imageLink, Integer id, String type) {
        mTitle = title;
        mOverview = overview;
        mDate = date;
        mRate = rate;
        mImageLink = imageLink;
        mId = id;
        this.type = type;
    }
    public MyMovieModel(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        type = type;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public Float getRate() {
        return mRate;
    }

    public void setRate(Float rate) {
        mRate = rate;
    }

    public String getImageLink() {
        return mImageLink;
    }

    public void setImageLink(String imageLink) {
        mImageLink = imageLink;
    }


}
