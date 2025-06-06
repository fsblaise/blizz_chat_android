package com.fsblaise.blizzchat.features.profile.presentation.state

import androidx.lifecycle.ViewModel
import com.fsblaise.blizzchat.features.core.domain.repository.SessionManagerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

// Here we can have the sessionManagerRepository, because the entire profile is based on that
// There won't be any ProfileRepository
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sessionManagerRepository: SessionManagerRepository
) : ViewModel() {
    private val _state = MutableStateFlow<ProfileState>(ProfileState.Initial)
    val state: StateFlow<ProfileState> = _state

    fun getSessions() {

    }
}