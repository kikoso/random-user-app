package com.enrique.randomuserapp.model.user


data class UserView (
    var id: String,
    var gender: String,
    var firstName: String,
    var lastName: String,
    var title: String,
    var email : String,
    var location: String,
    var picture: String
)