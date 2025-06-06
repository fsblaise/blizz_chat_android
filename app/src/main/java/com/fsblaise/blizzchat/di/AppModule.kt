package com.fsblaise.blizzchat.di

import android.content.Context
import android.content.SharedPreferences
import com.fsblaise.blizzchat.features.core.data.repository.SessionManagerRepositoryImpl
import com.fsblaise.blizzchat.features.core.data.utils.TokenInterceptor
import com.fsblaise.blizzchat.features.core.domain.repository.SessionManagerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Only provide common services here (No repositories or apis)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideTokenInterceptor(
        sessionManagerRepository: SessionManagerRepository
    ): TokenInterceptor {
        return TokenInterceptor(sessionManagerRepository)
    }

    // provide room db
}