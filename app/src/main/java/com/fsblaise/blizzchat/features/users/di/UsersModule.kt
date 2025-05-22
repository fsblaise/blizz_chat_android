package com.fsblaise.blizzchat.features.users.di

import com.fsblaise.blizzchat.features.users.data.data_source.UsersApi
import com.fsblaise.blizzchat.features.core.data.remote.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UsersModule {

    @Provides
    @Singleton
    fun provideUsersApi(): UsersApi {
        return RetrofitBuilder.createRetrofit(
            endpoint = "/users",
            serviceClass = UsersApi::class.java
        )
    }
}