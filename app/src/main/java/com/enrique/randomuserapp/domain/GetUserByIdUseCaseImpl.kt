package com.enrique.randomuserapp.domain

import com.enrique.randomuserapp.model.user.UserDomain
import com.enrique.randomuserapp.repository.UserRepository
import javax.inject.Inject
import com.enrique.randomuserapp.core.Result

class GetUserByIdUseCaseImpl @Inject constructor(private val userRepository: UserRepository) : GetUserByIdUseCase {
    override suspend fun getUserById(id: String): Result<UserDomain> =
        userRepository.getUserById(id)
}