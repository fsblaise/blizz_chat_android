package com.fsblaise.blizzchat.features.core.data.repository

import android.content.SharedPreferences
import com.fsblaise.blizzchat.BuildConfig
import com.fsblaise.blizzchat.features.auth.domain.model.UserProfile
import com.fsblaise.blizzchat.features.core.domain.model.UserPrefsSession
import com.fsblaise.blizzchat.features.core.domain.repository.SessionManagerRepository
import javax.inject.Inject
import androidx.core.content.edit
import kotlinx.serialization.json.Json

class SessionManagerRepositoryImpl @Inject constructor(
    private val prefs: SharedPreferences
) : SessionManagerRepository {
    private val sessionsKey = "userSessions"
    private val currentSessionKey = "currentSession"

    override suspend fun saveSession(
        email: String, apiUrl: String?, companyName: String?
    ) {
        val sessions = getSessions()?.toMutableList() ?: mutableListOf()
        val sessionExists = sessions.any {
            it.email == email && it.apiUrl == (apiUrl ?: BuildConfig.API_URL)
        }
        if (!sessionExists) {
            val sessionData = UserPrefsSession(
                email = email,
                apiUrl = apiUrl ?: BuildConfig.API_URL,
                companyName = companyName ?: "Blizz Chat",
                isActive = true,
                user = null,
                token = null
            )
            sessions.add(sessionData)
            prefs.edit {
                putString(sessionsKey, Json.encodeToString(sessions))
            }
        }
    }

    override suspend fun getSessions(): List<UserPrefsSession>? {
        val sessionsJson = prefs.getString(sessionsKey, null)
        return sessionsJson?.let {
            Json.decodeFromString<List<UserPrefsSession>>(it)
        }
    }

    override suspend fun updateSession(
        email: String, apiUrl: String, updatedSession: UserPrefsSession
    ) {
        val sessions = getSessions()?.toMutableList() ?: mutableListOf()
        val updatedSessions = sessions.map { session ->
            if (session.email == email && session.apiUrl == apiUrl) {
                updatedSession
            } else {
                session
            }
        }
        prefs.edit {
            putString(sessionsKey, Json.encodeToString(updatedSessions))
        }
    }

    override suspend fun handleAuth(
        token: String, user: UserProfile
    ) {
        val currentSession = getActiveSession()
        println(Json.encodeToString(currentSession))
        if (currentSession != null && currentSession.email == user.email) {
            val updatedCurrentSession = currentSession.copy(
                token = token,
                user = user
            )
            prefs.edit {
                putString(currentSessionKey, Json.encodeToString(updatedCurrentSession))
            }

            val sessions = getSessions()?.toMutableList() ?: mutableListOf()
            val updatedSessions = sessions.map { session ->
                if (session.email == user.email && session.apiUrl == currentSession.apiUrl) {
                    session.copy(
                        token = token,
                        user = user,
                    )
                } else {
                    session
                }
            }
            prefs.edit {
                putString(sessionsKey, Json.encodeToString(updatedSessions))
            }
        }
    }

    override suspend fun setActiveSession(email: String, apiUrl: String?) {
        var apiUrl = apiUrl ?: BuildConfig.API_URL
        val sessions = getSessions()?.toMutableList() ?: mutableListOf()
        var activeSession: UserPrefsSession? = null

        val updatedSessions = sessions.map { session ->
            if (session.email == email && session.apiUrl == apiUrl) {
                activeSession = session.copy(isActive = true)
                activeSession
            } else {
                session.copy(isActive = false)
            }
        }

        if (activeSession != null) {
            prefs.edit {
                putString(currentSessionKey, Json.encodeToString(activeSession))
            }
        }
        prefs.edit {
            putString(sessionsKey, Json.encodeToString(updatedSessions))
        }
    }

    override suspend fun removeActiveSession() {
        val currentSession = getActiveSession()
        currentSession?.let {
            prefs.edit {
                remove(currentSessionKey)
            }

            val sessions = getSessions()?.toMutableList() ?: mutableListOf()
            val updatedSessions = sessions.map { session ->
                if (session.email == it.email && session.apiUrl == it.apiUrl) {
                    session.copy(isActive = false)
                } else {
                    session
                }
            }

            prefs.edit {
                putString(sessionsKey, Json.encodeToString(updatedSessions))
            }
        }
    }

    override fun getActiveSession(): UserPrefsSession? {
        val currentSessionJson = prefs.getString(currentSessionKey, null)
        return currentSessionJson?.let {
            Json.decodeFromString<UserPrefsSession>(it)
        }
    }

    override suspend fun signOut() {
        val currentSession = getActiveSession()
        currentSession?.let {
            prefs.edit {
                remove(currentSessionKey)
            }

            val sessions = getSessions()?.toMutableList() ?: mutableListOf()
            val updatedSessions = sessions.filter { session ->
                session.email != it.email || session.apiUrl != it.apiUrl
            }

            prefs.edit {
                putString(sessionsKey, Json.encodeToString(updatedSessions))
            }
        }
    }

    override suspend fun signOutOtherSession(sessionToSignOut: UserPrefsSession) {
        val sessions = getSessions()?.toMutableList() ?: mutableListOf()
        val updatedSessions = sessions.filter { session ->
            session.email != sessionToSignOut.email || session.apiUrl != sessionToSignOut.apiUrl
        }

        prefs.edit {
            putString(sessionsKey, Json.encodeToString(updatedSessions))
        }
    }

    override suspend fun signOutAll() {
//        val sessions = getSessions()?.toMutableList() ?: mutableListOf()
        prefs.edit {
            putString(sessionsKey, Json.encodeToString(emptyList<UserPrefsSession>()))
            remove(currentSessionKey)
        }
    }
}