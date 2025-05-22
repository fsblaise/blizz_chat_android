package com.fsblaise.blizzchat.features.auth.data.repository

import com.fsblaise.blizzchat.features.auth.data.data_source.dto.AuthResponseDto
import com.fsblaise.blizzchat.features.auth.domain.model.UserProfile
import com.fsblaise.blizzchat.features.auth.domain.repository.AuthRepository

class AuthRepositoryImpl : AuthRepository {
    override suspend fun signIn(email: String, password: String): AuthResponseDto {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(
        email: String,
        password: String,
        fullName: String
    ): AuthResponseDto {
        TODO("Not yet implemented")
    }

    override suspend fun fetchUserByToken(): AuthResponseDto {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserProfile(userProfile: UserProfile): UserProfile {
        TODO("Not yet implemented")
    }
}