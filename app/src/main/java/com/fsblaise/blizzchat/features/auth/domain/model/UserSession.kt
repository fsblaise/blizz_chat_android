package com.fsblaise.blizzchat.features.auth.domain.model

data class UserSession(
    val token: String,
    val apiUrl: String,
    val user: UserProfile,
)
