package com.fsblaise.blizzchat.features.companies.presentation.state

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class CheckFormViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(CheckFormState())
    open val state: State<CheckFormState> = _state

    open fun onEmailChange(email: String) {
        val emailError = validateEmail(email)
        _state.value = _state.value.copy(
            email = email,
            emailError = emailError
        )
        validateForm()
    }

    private fun validateEmail(email: String): String? {
        val regex = Regex("^[a-zA-Z0-9.a-zA-Z0-9.!#\$%&'*+-/=?^_`{|}~]+@[a-zA-Z0-9]+\\.[a-zA-Z]+")
        return when {
            email.isBlank() -> "Email is required"
            !regex.matches(email) -> "Invalid email format"
            else -> null
        }
    }

    private fun validateForm() {
        val emailError = _state.value.emailError
        val email = _state.value.email

        _state.value = _state.value.copy(
            isValid = emailError == null
                    && email.isNotBlank()
        )
    }
}