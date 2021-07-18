package com.enrique.randomuserapp.persistence.dbEntity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.enrique.randomuserapp.model.user.UserDomain

@Entity
data class UserDBEntity (
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "email") val email: String? = "",
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "thumbnail") val picture: String,
) {
    fun toUserDomain() = UserDomain(
        id = id,
        gender = gender,
        firstName = firstName.orEmpty(),
        lastName = lastName.orEmpty(),
        title = title.orEmpty(),
        email = email.orEmpty(),
        location = location,
        picture = picture
    )
}