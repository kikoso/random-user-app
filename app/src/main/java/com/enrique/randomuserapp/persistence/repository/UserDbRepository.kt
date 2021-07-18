package com.enrique.randomuserapp.persistence.repository

import com.enrique.randomuserapp.persistence.dao.UserDao
import com.enrique.randomuserapp.persistence.dbEntity.UserDBEntity
import javax.inject.Inject

class UserDbRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun insertUser(user: UserDBEntity) {
        userDao.insertUser(user)
    }

    suspend fun getUserById(id: String) =
        userDao.getUserById(id)

    suspend fun insertUsers(users: List<UserDBEntity>) {
        userDao.insertUsers(users)
    }
}