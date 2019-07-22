package com.example.whattowatch.model.mymodel;

public class MyMovieModel {

    private  String mTitle;
    private  String mOverview;
    private  String mDate;
    private  Float mRate;
    private  String mImageLink;
    private Integer mId;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }
//private  int mType;

    public MyMovieModel(String title, String overview, String date, Float rate, String imageLink, Integer id) {
        mTitle = title;
        mOverview = overview;
        mDate = date;
        mRate = rate;
        mImageLink = imageLink;
        mId = id;
    }
    public MyMovieModel(){}

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
