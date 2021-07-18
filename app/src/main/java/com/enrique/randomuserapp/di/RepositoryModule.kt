package com.enrique.randomuserapp.di

import com.enrique.randomuserapp.network.RandomUserService
import com.enrique.randomuserapp.persistence.repository.UserDbRepository
import com.enrique.randomuserapp.repository.UserRepository
import com.enrique.randomuserapp.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideUserRepository(
        apiService: RandomUserService,
        userDbRepository: UserDbRepository
    ): UserRepository {
        return UserRepositoryImpl(apiService, userDbRepository)
    }


    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}