package com.fsblaise.blizzchat.features.companies.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Member(
    val fullName: String,
    val email: String,
    val role: String,
)
