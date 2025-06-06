package com.fsblaise.blizzchat.features.core.di

import android.content.SharedPreferences
import com.fsblaise.blizzchat.features.companies.data.data_source.CompaniesApi
import com.fsblaise.blizzchat.features.companies.data.repository.CompaniesRepositoryImpl
import com.fsblaise.blizzchat.features.companies.domain.repository.CompaniesRepository
import com.fsblaise.blizzchat.features.core.data.data_source.ConnectionApi
import com.fsblaise.blizzchat.features.core.data.remote.RetrofitBuilder
import com.fsblaise.blizzchat.features.core.data.repository.ConnectionRepositoryImpl
import com.fsblaise.blizzchat.features.core.data.repository.SessionManagerRepositoryImpl
import com.fsblaise.blizzchat.features.core.data.utils.TokenInterceptor
import com.fsblaise.blizzchat.features.core.domain.repository.ConnectionRepository
import com.fsblaise.blizzchat.features.core.domain.repository.SessionManagerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Only provide common apis and repositories here
@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun provideSessionManagerRepository(
        sharedPreferences: SharedPreferences
    ): SessionManagerRepository {
        return SessionManagerRepositoryImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideConnectionApi(
        interceptor: TokenInterceptor
    ): ConnectionApi {
        return RetrofitBuilder.createRetrofit(
            endpoint = "/users/",
            serviceClass = ConnectionApi::class.java,
            interceptor = interceptor
        )
    }

    @Provides
    @Singleton
    fun provideConnectionRepository(
        connectionApi: ConnectionApi,
    ): ConnectionRepository {
        return ConnectionRepositoryImpl(connectionApi)
    }
}