package com.enrique.randomuserapp.repository

import com.enrique.randomuserapp.core.Failure
import com.enrique.randomuserapp.core.Result
import com.enrique.randomuserapp.model.user.UserDomain
import com.enrique.randomuserapp.network.RandomUserService
import com.enrique.randomuserapp.persistence.dbEntity.UserDBEntity
import com.enrique.randomuserapp.persistence.repository.UserDbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: RandomUserService,
    private val userDbRepository: UserDbRepository
) :
    UserRepository {
    override suspend fun getUsersList(): Result<List<UserDomain>> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getUsersList().execute()
            when {
                response.isSuccessful -> {
                    response.body()?.let { getAllUsersResponse ->
                        val listUserDomain = mutableListOf<UserDomain>()
                        getAllUsersResponse.users.forEach {
                            listUserDomain.add(it.toUserDomain())
                        }
                        val listUserDb = mutableListOf<UserDBEntity>()
                        listUserDomain.forEach {
                            listUserDb.add(it.toUserDBEntity())
                        }
                        userDbRepository.insertUsers(listUserDb)

                        return@withContext Result.Success(listUserDomain)
                    }
                    return@withContext Result.Success(emptyList())
                }
                else -> return@withContext  Result.Error(Failure.NetworkConnection)
            }
        }
    }

    override suspend fun getUserById(id: String): Result<UserDomain> {
        return withContext(Dispatchers.IO) {
            try {
                val user = userDbRepository.getUserById(id)
                return@withContext Result.Success(user.toUserDomain())
            } catch (e: Exception) {
                return@withContext Result.Error(Failure.GeneralFailure)
            }
        }
    }
}