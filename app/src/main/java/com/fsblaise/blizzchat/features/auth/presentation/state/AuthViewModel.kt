package com.fsblaise.blizzchat.features.auth.presentation.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fsblaise.blizzchat.BuildConfig
import com.fsblaise.blizzchat.features.auth.domain.model.UserSession
import com.fsblaise.blizzchat.features.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow<AuthState>(AuthState.Initial)
    val state: StateFlow<AuthState> = _state

    init {
        getLoggedInUser()
    }

    fun signUp(email: String, password: String, fullName: String) {
        _state.value = AuthState.Fetching
        viewModelScope.launch {
            try {
                val response = repository.signUp(email, password, fullName)
                val userSession = UserSession(
                    user = response.user,
                    token = response.token,
                    // TODO: CompaniesRepository.apiUrl
                    apiUrl = BuildConfig.API_URL,
                )
                _state.value = AuthState.Authenticated(userSession)
            } catch (e: Exception) {
                _state.value = AuthState.Error(e.message ?: "An error occurred")
            }
        }
    }

    fun signIn(email: String, password: String) {
        _state.value = AuthState.Fetching
        viewModelScope.launch {
            try {
                val response = repository.signIn(email, password)
                val userSession = UserSession(
                    user = response.user,
                    token = response.token,
                    // TODO: CompaniesRepository.apiUrl
                    apiUrl = BuildConfig.API_URL,
                )
                _state.value = AuthState.Authenticated(userSession)
            } catch (e: Exception) {
                _state.value = AuthState.Error(e.message ?: "An error occurred")
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            try {
                repository.signOut()
                _state.value = AuthState.Unauthenticated
            } catch (e: Exception) {
                _state.value = AuthState.Error(e.message ?: "An error occurred")
            }
        }
    }

    fun getLoggedInUser() {
        _state.value = AuthState.Fetching
        viewModelScope.launch {
            try {
                val response = repository.getLoggedInUser()
                val userSession = UserSession(
                    user = response.user,
                    token = response.token,
                    // TODO: CompaniesRepository.apiUrl
                    apiUrl = "https://blizz-chat-backend-0aaba721d2ed.herokuapp.com/",
                )
                _state.value = AuthState.Authenticated(userSession)
            } catch (e: Exception) {
                _state.value = AuthState.Error(e.message ?: "An error occurred")
            }
        }
    }
}