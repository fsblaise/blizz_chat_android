package com.fsblaise.blizzchat.features.settings.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Preference(
    val darkMode: Boolean,
    val syncDarkMode: Boolean,
    val preferredColor: String,
)
