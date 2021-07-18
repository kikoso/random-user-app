package com.enrique.randomuserapp.model.user

import com.enrique.randomuserapp.persistence.dbEntity.UserDBEntity

data class UserDomain(
    var id: String,
    var gender: String,
    var firstName: String,
    var lastName: String,
    var title: String,
    var email: String,
    var location: String,
    var picture: String
) {
    fun toUserView() = UserView(
        id = id,
        gender = gender,
        firstName = firstName,
        lastName = lastName,
        title = title,
        email = email,
        location = location,
        picture = picture
    )

    fun toUserDBEntity() = UserDBEntity(
        id = id,
        gender = gender,
        firstName = firstName,
        lastName = lastName,
        title = title,
        email = email,
        location = location,
        picture = picture
    )
}