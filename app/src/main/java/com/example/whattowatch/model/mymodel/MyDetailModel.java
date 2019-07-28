package com.example.whattowatch.model.mymodel;

import java.util.List;

public class MyDetailModel {

    private Integer id;
    private String name;
    private String posterLink;
    private String backdroLink;
    private Integer runtime;
    private Float rate;
    private String overview;
    private String releaseDate;
    private List<MyVideoModel> videos;

    public List <MyVideoModel> getVideos() {
        return videos;
    }

    public void setVideos(List<MyVideoModel> videos) {
        this.videos = videos;
    }

    public MyDetailModel(Integer id, String name, String posterLink, String backdroLink, Integer runtime, Float rate, String overview, String releaseDate) {
        this.id = id;
        this.name = name;
        this.posterLink = posterLink;
        this.backdroLink = backdroLink;
        this.runtime = runtime;
        this.rate = rate;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public MyDetailModel(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterLink() {
        return posterLink;
    }

    public void setPosterLink(String posterLink) {
        this.posterLink = posterLink;
    }

    public String getBackdroLink() {
        return backdroLink;
    }

    public void setBackdroLink(String backdroLink) {
        this.backdroLink = backdroLink;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }



}
