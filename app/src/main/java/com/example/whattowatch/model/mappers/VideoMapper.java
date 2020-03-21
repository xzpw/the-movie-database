package com.example.whattowatch.model.mappers;

import com.example.whattowatch.model.mymodel.MyVideoModel;
import com.example.whattowatch.model.response.video.MovieVideos;

import java.util.ArrayList;
import java.util.List;

public class VideoMapper {
    public static MyVideoModel convertToMyVideoModel(MovieVideos movieVideos){

        return new MyVideoModel(movieVideos.getName(),movieVideos.getKey());
    }

    public static List<MyVideoModel> convertToMyVideoList(List<MovieVideos> movieVideos){
        List<MyVideoModel> videoModelList = new ArrayList<>();

        for(MovieVideos mv: movieVideos){
            videoModelList.add(convertToMyVideoModel(mv));
        }
        return videoModelList;
    }
}