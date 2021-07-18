package com.enrique.randomuserapp.network

import com.enrique.randomuserapp.model.user.GetAllUsersApiResponse
import retrofit2.Call
import javax.inject.Inject

class RandomUserService @Inject constructor(private val api: RandomUserApi){
    fun getUsersList(): Call<GetAllUsersApiResponse> {
        return api.getUser()
    }
}