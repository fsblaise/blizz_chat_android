package com.fsblaise.blizzchat.features.stories.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Story(
    val id: String,
    val caption: String,
    val extension: String,
    val imgUrl: String,
    val timestamp: String,
    val email: String,
    val fullName: String
)
