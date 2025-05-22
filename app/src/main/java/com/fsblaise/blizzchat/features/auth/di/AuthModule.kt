package com.fsblaise.blizzchat.features.auth.di

import com.fsblaise.blizzchat.features.auth.data.data_source.AuthApi
import com.fsblaise.blizzchat.features.users.data.data_source.UsersApi
import com.fsblaise.blizzchat.features.core.data.remote.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return RetrofitBuilder.createRetrofit(
            endpoint = "/users",
            serviceClass = AuthApi::class.java
        )
    }
}