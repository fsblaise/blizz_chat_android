package com.fsblaise.blizzchat.features.auth.domain.repository

import com.fsblaise.blizzchat.features.auth.data.data_source.dto.AuthResponseDto
import com.fsblaise.blizzchat.features.auth.domain.model.UserProfile

interface AuthRepository {
    suspend fun signIn(email: String, password: String): AuthResponseDto
    suspend fun signUp(email: String, password: String, fullName: String): AuthResponseDto
    fun getToken(): String?
    fun removeActiveSession()
    fun signOut(): Boolean
    suspend fun getLoggedInUser(): UserProfile
}