package com.enrique.randomuserapp.domain

import com.enrique.randomuserapp.model.user.UserDomain
import com.enrique.randomuserapp.repository.UserRepository
import javax.inject.Inject
import com.enrique.randomuserapp.core.Result

class GetRandomUsersUseCaseImpl @Inject constructor(private val userRepository: UserRepository) : GetRandomUsersUseCase {
    override suspend fun getUsers(): Result<List<UserDomain>> {
        return userRepository.getUsersList()
    }

}