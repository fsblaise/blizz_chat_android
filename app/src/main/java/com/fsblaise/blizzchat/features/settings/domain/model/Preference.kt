package com.fsblaise.blizzchat.features.settings.domain.model

data class Preference(
    val darkMode: Boolean,
    val syncDarkMode: Boolean,
    val preferredColor: String,
)
