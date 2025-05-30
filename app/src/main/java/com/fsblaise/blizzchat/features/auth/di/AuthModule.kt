package com.fsblaise.blizzchat.features.auth.di

import com.fsblaise.blizzchat.features.auth.data.data_source.AuthApi
import com.fsblaise.blizzchat.features.auth.data.repository.AuthRepositoryImpl
import com.fsblaise.blizzchat.features.auth.domain.repository.AuthRepository
import com.fsblaise.blizzchat.features.core.data.remote.RetrofitBuilder
import com.fsblaise.blizzchat.features.core.data.utils.TokenInterceptor
import com.fsblaise.blizzchat.features.core.domain.repository.SessionManagerRepository
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
    fun provideAuthApi(
        tokenInterceptor: TokenInterceptor
    ): AuthApi {
        return RetrofitBuilder.createRetrofit(
            endpoint = "/auth/",
            serviceClass = AuthApi::class.java,
            interceptor = tokenInterceptor
        )
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApi: AuthApi,
        sessionManagerRepository: SessionManagerRepository
    ): AuthRepository {
        return AuthRepositoryImpl(authApi, sessionManagerRepository)
    }
}