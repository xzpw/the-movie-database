package com.example.whattowatch.model.mappers;

import com.example.whattowatch.model.mymodel.MyDetailModel;
import com.example.whattowatch.model.response.detail.DetailMovie;

public class DetailMapper {

    public static MyDetailModel convertToMyDetailModel(DetailMovie detail){
        return new MyDetailModel(
                detail.getId(),
                detail.getTitle(),
                detail.getPosterPath(),
                detail.getBackdropPath(),
                detail.getRuntime(),
                detail.getVoteAverage().floatValue(),
                detail.getOverview(),
                detail.getReleaseDate());
    }
}
