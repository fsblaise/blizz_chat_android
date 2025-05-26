package com.fsblaise.blizzchat.features.auth.presentation.state

data class SignUpFormState(
    val email: String = "",
    val password: String = "",
    val rePassword: String = "",
    val fullName: String = "",
    val isStepOneValid: Boolean = false,
    val isValid: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val rePasswordError: String? = null,
    val fullNameError: String? = null,
)
