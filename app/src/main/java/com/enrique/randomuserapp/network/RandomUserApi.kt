package com.enrique.randomuserapp.network

import com.enrique.randomuserapp.model.user.GetAllUsersApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface RandomUserApi {
    @GET("api/?format=json&results=10")
    fun getUser(): Call<GetAllUsersApiResponse>
}