package com.enrique.randomuserapp.persistence.dao

import androidx.room.*
import com.enrique.randomuserapp.persistence.dbEntity.UserDBEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserDBEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertUsers(users: List<UserDBEntity>)

    @Query("SELECT * FROM UserDBEntity")
    suspend fun getUser(): UserDBEntity

    @Query("SELECT * FROM UserDBEntity WHERE id = :id")
    suspend fun getUserById(id: String): UserDBEntity
}