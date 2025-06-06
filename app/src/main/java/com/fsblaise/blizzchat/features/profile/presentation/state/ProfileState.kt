package com.fsblaise.blizzchat.features.profile.presentation.state

import com.fsblaise.blizzchat.features.core.domain.model.UserPrefsSession

sealed interface ProfileState {
    data object Initial : ProfileState
    data object Fetching : ProfileState
    data class Fetched(
        val sessions: List<UserPrefsSession>
    ) : ProfileState
    data class Error(val message: String) : ProfileState
}
