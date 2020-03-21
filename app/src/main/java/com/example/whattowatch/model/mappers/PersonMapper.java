package com.example.whattowatch.model.mappers;

import com.example.whattowatch.model.mymodel.MyPersonModel;
import com.example.whattowatch.model.response.person.Person;

public class PersonMapper {

    public static MyPersonModel convertPerson(Person person){
        return new MyPersonModel(
                person.getBirthday(),
                person.getKnownForDepartment(),
                person.getId(),
                person.getName(),
                person.getGender(),
                person.getBiography(),
                person.getPopularity(),
                person.getPlaceOfBirth(),
                person.getProfilePath()
        );
    }
}
