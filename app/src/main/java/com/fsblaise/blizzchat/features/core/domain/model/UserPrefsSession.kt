package com.fsblaise.blizzchat.features.core.domain.model

import com.fsblaise.blizzchat.features.auth.domain.model.UserProfile
import kotlinx.serialization.Serializable

@Serializable
data class UserPrefsSession(
    val email: String,
    val apiUrl: String,
    val companyName: String,
    val isActive: Boolean,
    val token: String? = null,
    val user: UserProfile? = null
)
