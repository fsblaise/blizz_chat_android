package com.fsblaise.blizzchat.features.users.di

import com.fsblaise.blizzchat.features.core.data.remote.RetrofitBuilder
import com.fsblaise.blizzchat.features.core.data.utils.TokenInterceptor
import com.fsblaise.blizzchat.features.users.data.data_source.UsersApi
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
    fun provideUsersApi(
        tokenInterceptor: TokenInterceptor
    ): UsersApi {
        return RetrofitBuilder.createRetrofit(
            endpoint = "/users/",
            serviceClass = UsersApi::class.java,
            interceptor = tokenInterceptor // Assuming no interceptor is needed for UsersApi
        )
    }
}