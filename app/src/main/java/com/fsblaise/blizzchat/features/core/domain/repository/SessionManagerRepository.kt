package com.fsblaise.blizzchat.features.core.domain.repository

import com.fsblaise.blizzchat.features.auth.domain.model.UserProfile
import com.fsblaise.blizzchat.features.core.domain.model.UserPrefsSession

interface SessionManagerRepository {
    fun saveSession(
        email: String,
        apiUrl: String? = null,
        companyName: String? = null
    )

    fun getSessions(): List<UserPrefsSession>?

    fun updateSession(
        email: String,
        apiUrl: String,
        updatedSession: UserPrefsSession
    )

    fun handleAuth(
        token: String,
        user: UserProfile
    )

    fun setActiveSession(
        email: String,
        apiUrl: String? = null,
    )

    fun removeActiveSession()

    fun getActiveSession(): UserPrefsSession?

    fun signOut()

    fun signOutOtherSession(
        sessionToSignOut: UserPrefsSession
    )

    fun signOutAll()
}