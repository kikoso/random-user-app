package com.enrique.randomuserapp.di

import android.content.Context
import com.enrique.randomuserapp.persistence.RandomUserAppDatabase
import com.enrique.randomuserapp.persistence.dao.UserDao
import com.enrique.randomuserapp.persistence.repository.UserDbRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = RandomUserAppDatabase.getInstance(context)

    @Provides
    fun provideUserDao(database: RandomUserAppDatabase) = database.userDao()

    @Provides
    fun provideUserDBRepository(userDao: UserDao): UserDbRepository {
        return UserDbRepository(userDao)
    }

}