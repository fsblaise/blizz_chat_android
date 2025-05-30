package com.fsblaise.blizzchat.features.core.domain.repository

import com.fsblaise.blizzchat.features.auth.domain.model.UserProfile
import com.fsblaise.blizzchat.features.core.domain.model.UserPrefsSession

interface SessionManagerRepository {
    suspend fun saveSession(
        email: String,
        apiUrl: String? = null,
        companyName: String? = null
    )

    suspend fun getSessions(): List<UserPrefsSession>?

    suspend fun updateSession(
        email: String,
        apiUrl: String,
        updatedSession: UserPrefsSession
    )

    suspend fun handleAuth(
        token: String,
        user: UserProfile
    )

    suspend fun setActiveSession(
        email: String,
        apiUrl: String? = null,
    )

    suspend fun removeActiveSession()

    fun getActiveSession(): UserPrefsSession?

    suspend fun signOut()

    suspend fun signOutOtherSession(
        sessionToSignOut: UserPrefsSession
    )

    suspend fun signOutAll()
}