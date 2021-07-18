package com.enrique.randomuserapp.repository

import com.enrique.randomuserapp.core.Result
import com.enrique.randomuserapp.model.user.UserDomain

interface UserRepository  {
    suspend fun getUsersList(): Result<List<UserDomain>>
    suspend fun getUserById(id: String): Result<UserDomain>
}