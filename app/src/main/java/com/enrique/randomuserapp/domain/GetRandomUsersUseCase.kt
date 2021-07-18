package com.enrique.randomuserapp.domain

import com.enrique.randomuserapp.model.user.UserDomain
import com.enrique.randomuserapp.core.Result

interface GetRandomUsersUseCase {
    suspend fun getUsers() : Result<List<UserDomain>>
}