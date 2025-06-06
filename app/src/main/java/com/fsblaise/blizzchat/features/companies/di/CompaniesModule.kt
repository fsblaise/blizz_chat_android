package com.fsblaise.blizzchat.features.companies.di

import com.fsblaise.blizzchat.features.companies.data.data_source.CompaniesApi
import com.fsblaise.blizzchat.features.companies.data.repository.CompaniesRepositoryImpl
import com.fsblaise.blizzchat.features.companies.domain.repository.CompaniesRepository
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
object CompaniesModule {

    // Do not pass apiUrl as a parameter here
    @Provides
    @Singleton
    fun provideCompaniesApi(
        interceptor: TokenInterceptor
    ): CompaniesApi {
        return RetrofitBuilder.createRetrofit(
            endpoint = "/companies/",
            serviceClass = CompaniesApi::class.java,
            interceptor = interceptor
        )
    }

    @Provides
    @Singleton
    fun provideCompaniesRepository(
        companiesApi: CompaniesApi,
        sessionManagerRepository: SessionManagerRepository
    ): CompaniesRepository {
        return CompaniesRepositoryImpl(companiesApi, sessionManagerRepository)
    }
}