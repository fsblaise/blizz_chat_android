package com.fsblaise.blizzchat.features.companies.presentation.state

data class CheckFormState(
    val email: String = "",
    val isValid: Boolean = false,
    val emailError: String? = null,
)