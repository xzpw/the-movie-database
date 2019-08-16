package com.example.whattowatch.model.mymodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyPersonModel {


    private String birthday;

    private String knownForDepartment;

    private Integer id;

    private String name;

    private Integer gender;

    private String biography;

    private Double popularity;

    private String placeOfBirth;

    private String profilePath;

    public MyPersonModel(String birthday, String knownForDepartment, Integer id, String name, Integer gender, String biography, Double popularity, String placeOfBirth, String profilePath) {
        this.birthday = birthday;
        this.knownForDepartment = knownForDepartment;
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.biography = biography;
        this.popularity = popularity;
        this.placeOfBirth = placeOfBirth;
        this.profilePath = profilePath;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public void setKnownForDepartment(String knownForDepartment) {
        this.knownForDepartment = knownForDepartment;
    }

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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
