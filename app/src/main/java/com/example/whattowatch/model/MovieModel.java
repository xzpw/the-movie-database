package com.example.whattowatch.model;

public class MovieModel {

    private  String mTitle;
    private  String mOverview;
    private  String mDate;
    private  String mRate;
    private  String mImageLink;
    //private  int mType;

    public MovieModel(String title, String overview, String date, String rate, String imageLink) {
        mTitle = title;
        mOverview = overview;
        mDate = date;
        mRate = rate;
        mImageLink = imageLink;
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

    public String getRate() {
        return mRate;
    }

    public void setRate(String rate) {
        mRate = rate;
    }

    public String getImageLink() {
        return mImageLink;
    }

    public void setImageLink(String imageLink) {
        mImageLink = imageLink;
    }


}
