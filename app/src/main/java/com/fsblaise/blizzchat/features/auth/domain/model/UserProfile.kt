package com.fsblaise.blizzchat.features.auth.domain.model

import com.fsblaise.blizzchat.features.settings.domain.model.Preference
import com.fsblaise.blizzchat.features.settings.domain.model.SecuritySettings
import com.fsblaise.blizzchat.features.users.domain.model.Contact

data class UserProfile(
    val fullName: String,
    val email: String,
    val birthDay: String?,
    val phoneNumber: String?,
    val country: String?,
    val city: String?,
    val location: String?,
    val gender: String?,
    val contacts: List<Contact>,
    val profileUrl: String?,
    val unreadMessagesSum: Int,
    val unreadMessages: Map<String, String?>?,
    val preferences: Preference?,
    val securitySettings: SecuritySettings?,
    val isOnline: Boolean,
)
