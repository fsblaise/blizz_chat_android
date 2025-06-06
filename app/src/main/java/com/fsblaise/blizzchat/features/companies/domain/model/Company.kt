package com.fsblaise.blizzchat.features.companies.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val name: String,
    val apiUrl: String,
    val members: List<Member>
)
