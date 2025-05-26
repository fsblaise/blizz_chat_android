package com.fsblaise.blizzchat.features.auth.presentation.state

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInFormViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(SignInFormState())
    val state: State<SignInFormState> = _state

    fun onEmailChange(email: String) {
        val emailError = validateEmail(email)
        _state.value = _state.value.copy(
            email = email,
            emailError = emailError
        )
        validateForm()
    }

    fun onPasswordChange(password: String) {
        val passwordError = validatePassword(password)
        _state.value = _state.value.copy(
            password = password,
            passwordError = passwordError
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

    private fun validatePassword(password: String): String? {
        return when {
            password.isBlank() -> "Password is required"
            password.length < 6 -> "Password must be at least 6 characters"
            else -> null
        }
    }

    private fun validateForm() {
        val emailError = _state.value.emailError
        val passwordError = _state.value.passwordError
        _state.value = _state.value.copy(
            isValid = emailError == null && passwordError == null
        )
    }
}