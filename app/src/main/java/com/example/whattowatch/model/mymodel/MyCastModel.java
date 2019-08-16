package com.example.whattowatch.model.mymodel;

public class MyCastModel {

    private Integer id;
    private String character;
    private String name;
    private String profilePath;

    public MyCastModel(Integer id, String character, String name, String profilePath) {
        this.id = id;
        this.character = character;
        this.name = name;
        this.profilePath = profilePath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
