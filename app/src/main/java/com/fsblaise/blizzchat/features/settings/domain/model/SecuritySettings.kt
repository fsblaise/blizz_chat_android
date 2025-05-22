package com.fsblaise.blizzchat.features.settings.domain.model

data class SecuritySettings(
    val showBirthDay: Boolean,
    val showHomePlace: Boolean,
    val showLocation: Boolean,
    val showGender: Boolean,
    val showPhoneNumber: Boolean
)
