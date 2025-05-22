package com.fsblaise.blizzchat.features.auth.data.data_source.dto

import com.fsblaise.blizzchat.features.auth.domain.model.UserProfile

data class AuthResponseDto(
    val token: String,
    val user: UserProfile,
)
