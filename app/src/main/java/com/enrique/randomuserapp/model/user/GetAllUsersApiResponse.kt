package com.enrique.randomuserapp.model.user

import com.google.gson.annotations.SerializedName

class GetAllUsersApiResponse {
    @SerializedName("results")
    var users: List<UserApiEntity> = emptyList()


}