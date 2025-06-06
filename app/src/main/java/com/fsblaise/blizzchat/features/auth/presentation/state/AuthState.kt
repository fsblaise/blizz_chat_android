package com.fsblaise.blizzchat.features.auth.presentation.state

import com.fsblaise.blizzchat.features.auth.domain.model.UserSession

sealed interface AuthState {
    data object Initial : AuthState
    data object Fetching : AuthState
    data object Unauthenticated : AuthState
    data class Authenticated(val userSession: UserSession) : AuthState
    data object HelloSuccess : AuthState
    data class Error(val message: String) : AuthState
}
