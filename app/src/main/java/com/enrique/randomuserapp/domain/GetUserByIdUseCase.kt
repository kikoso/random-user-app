package com.enrique.randomuserapp.domain

import com.enrique.randomuserapp.model.user.UserDomain
import com.enrique.randomuserapp.core.Result

interface GetUserByIdUseCase {
    suspend fun getUserById(id: String) : Result<UserDomain>
}