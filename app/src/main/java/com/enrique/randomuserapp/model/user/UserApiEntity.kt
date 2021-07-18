package com.enrique.randomuserapp.model.user

import com.google.gson.annotations.SerializedName

class UserApiEntity {
    @SerializedName("id")
    var id: ID = ID()

    @SerializedName("gender")
    var gender: String = ""

    @SerializedName("email")
    var email: String = ""

    @SerializedName("name")
    var name: Name = Name()

    @SerializedName("location")
    var location: Location = Location()

    @SerializedName("picture")
    var picture: Picture = Picture()


    class Name {
        @SerializedName("title")
        var title: String = ""

        @SerializedName("first")
        var first: String = ""

        @SerializedName("last")
        var last: String = ""
    }

    // FIX: using the ID as ID would be a fantastic idea if the API would not return null from time to time.
    class ID {
        @SerializedName("name")
        var name: String = ""

        @SerializedName("value")
        var value: String = ""

    }

    class Location {
        @SerializedName("city")
        var city: String = ""

        @SerializedName("state")
        var state: String = ""

        @SerializedName("country")
        var country: String = ""
    }

    class Picture {
        @SerializedName("large")
        var large: String = ""

        @SerializedName("medium")
        var medium: String = ""

        @SerializedName("thumbnail")
        var thumbnail: String = ""
    }

    fun toUserDomain() = UserDomain(
        id = name.hashCode().toString(),
        gender = gender,
        firstName = name.first,
        lastName = name.last,
        title = name.title,
        email = email,
        picture = picture.thumbnail,
        location = location.country
    )

}