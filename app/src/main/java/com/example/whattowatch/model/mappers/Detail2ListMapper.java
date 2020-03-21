package com.example.whattowatch.model.mappers;

import android.util.Log;

import com.example.whattowatch.model.mymodel.MyDetailModel;
import com.example.whattowatch.model.mymodel.MyMovieModel;

import java.util.ArrayList;
import java.util.List;

public class Detail2ListMapper {

    public static List<MyMovieModel> convert2ListModel(List<MyDetailModel> detailModelList){
        List<MyMovieModel> list = new ArrayList<>();

        for(MyDetailModel detailModel: detailModelList){
           MyMovieModel model = new MyMovieModel(detailModel.getName(),
                   detailModel.getOverview(),
                   detailModel.getReleaseDate(),
                   detailModel.getRate(),
                   detailModel.getPosterLink(),
                   detailModel.getId(),
                   "favorite");
            list.add(model);
            Log.e("mylog","Detail2ListMapper");
        }
        return list;
    }
}
