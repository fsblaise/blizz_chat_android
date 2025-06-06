package com.fsblaise.blizzchat.features.users.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class Contact(
    val nickname: String,
    val fullName: String,
    val email: String,
)
