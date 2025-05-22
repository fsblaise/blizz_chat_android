package com.fsblaise.blizzchat.features.auth.domain.model

data class User(
    val fullName: String,
    val email: String,
    val birthDay: String?,
    val phoneNumber: String?,
    val country: String?,
    val city: String?,
    val location: String?,
    val gender: String?,
    val profileUrl: String?,
    val isOnline: Boolean,
)
