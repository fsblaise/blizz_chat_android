package com.fsblaise.blizzchat.features.auth.presentation.state

data class SignInFormState(
    val email: String = "",
    val password: String = "",
    val isValid: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
)
