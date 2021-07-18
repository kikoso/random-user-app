package com.enrique.randomuserapp.di

import com.enrique.randomuserapp.domain.GetRandomUsersUseCase
import com.enrique.randomuserapp.domain.GetRandomUsersUseCaseImpl
import com.enrique.randomuserapp.domain.GetUserByIdUseCase
import com.enrique.randomuserapp.domain.GetUserByIdUseCaseImpl
import com.enrique.randomuserapp.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Singleton
    @Provides
    fun provideUserUseCase(
        userRepository: UserRepository
    ): GetRandomUsersUseCase {
        return GetRandomUsersUseCaseImpl(userRepository)
    }

    @Singleton
    @Provides
    fun provideUserByIdUseCase(
        userRepository: UserRepository
    ): GetUserByIdUseCase {
        return GetUserByIdUseCaseImpl(userRepository)
    }
}