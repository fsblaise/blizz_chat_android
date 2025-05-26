package com.fsblaise.blizzchat.features.auth.presentation.state

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpFormViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(SignUpFormState())
    val state: State<SignUpFormState> = _state

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

    fun onRePasswordChange(rePassword: String) {
        val rePasswordError = validateRePassword(rePassword)
        _state.value = _state.value.copy(
            rePassword = rePassword,
            rePasswordError = rePasswordError
        )
        validateForm()
    }

    fun onFullNameChange(fullName: String) {
        val fullNameError = validateFullName(fullName)
        _state.value = _state.value.copy(
            fullName = fullName,
            fullNameError = fullNameError
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

    private fun validateRePassword(rePassword: String): String? {
        return when {
            rePassword.isBlank() -> "Re-entering password is required"
            rePassword.length < 6 -> "Re-entered password must be at least 6 characters"
            rePassword != _state.value.password -> "Passwords do not match"
            else -> null
        }
    }

    private fun validateFullName(fullName: String): String? {
        return when {
            fullName.isBlank() -> "Full name is required"
            fullName.length < 6 -> "Full name must be at least 6 characters"
            else -> null
        }
    }

    private fun validateForm() {
        val emailError = _state.value.emailError
        val passwordError = _state.value.passwordError
        val rePasswordError = _state.value.rePasswordError
        val fullNameError = _state.value.fullNameError
        val email = _state.value.email
        val password = _state.value.password
        val rePassword = _state.value.rePassword
        val fullName = _state.value.fullName

        _state.value = _state.value.copy(
            isStepOneValid = emailError == null &&
                    passwordError == null &&
                    rePasswordError == null &&
                    email.isNotBlank() &&
                    password.isNotBlank() &&
                    rePassword.isNotBlank(),
        )

        _state.value = _state.value.copy(
            isValid = emailError == null &&
                    passwordError == null &&
                    rePasswordError == null &&
                    fullNameError == null &&
                    email.isNotBlank() &&
                    password.isNotBlank() &&
                    rePassword.isNotBlank() &&
                    fullName.isNotBlank(),
        )
    }
}