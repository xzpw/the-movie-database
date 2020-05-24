package com.example.whattowatch.model.mappers

import com.example.whattowatch.model.mymodel.MyPersonModel
import com.example.whattowatch.model.response.person.Person

object PersonMapper {
    fun convertPerson(person: Person): MyPersonModel {
        return MyPersonModel(
                person.birthday,
                person.knownForDepartment,
                person.id,
                person.name,
                person.gender,
                person.biography,
                person.popularity,
                person.placeOfBirth,
                person.profilePath
        )
    }
}