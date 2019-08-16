package com.example.whattowatch.model.mappers;

import com.example.whattowatch.model.mymodel.MyCastModel;
import com.example.whattowatch.model.response.cradits.Cast;

import java.util.ArrayList;
import java.util.List;

public class CastMapper {
    public static MyCastModel convertMyCastModel(Cast cast){
        return new MyCastModel(cast.getId(),
                               cast.getCharacter(),
                               cast.getName(),
                               cast.getProfilePath());
    }
    public static List<MyCastModel> convertListMyCastModel(List<Cast> casts){
        List<MyCastModel> myCastModels = new ArrayList<>();
        for(Cast cast : casts){
            myCastModels.add(convertMyCastModel(cast));
        }
        return myCastModels;
    }
}
