package com.fsblaise.blizzchat.features.auth.data.repository

import com.fsblaise.blizzchat.features.auth.data.data_source.AuthApi
import com.fsblaise.blizzchat.features.auth.data.data_source.dto.AuthResponseDto
import com.fsblaise.blizzchat.features.auth.data.data_source.dto.SignInDto
import com.fsblaise.blizzchat.features.auth.data.data_source.dto.SignUpDto
import com.fsblaise.blizzchat.features.auth.domain.repository.AuthRepository
import com.fsblaise.blizzchat.features.core.domain.repository.SessionManagerRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val sessionManagerRepository: SessionManagerRepository
) : AuthRepository {
    override suspend fun signIn(email: String, password: String): AuthResponseDto {
        val response = api.signIn(SignInDto(email, password))
        sessionManagerRepository.handleAuth(response.token, response.user)
        println(response.user)
        println(response.token)
        return response
    }

    override suspend fun signUp(
        email: String,
        password: String,
        fullName: String
    ): AuthResponseDto {
        val response = api.signUp(SignUpDto(email, password, fullName))
        sessionManagerRepository.handleAuth(response.token, response.user)
        println(response.user)
        println(response.token)
        return response
    }

    override suspend fun signOut(): Boolean {
        sessionManagerRepository.signOut()
        return true
    }

    override suspend fun getLoggedInUser(): AuthResponseDto {
        // no need to fetch token, because the interceptor will handle it
        return api.fetchUserByToken()
    }
}