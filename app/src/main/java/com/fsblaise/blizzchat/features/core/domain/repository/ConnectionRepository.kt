package com.fsblaise.blizzchat.features.core.domain.repository

interface ConnectionRepository {
    suspend fun hello(): Boolean
}