package com.fsblaise.blizzchat.features.core.data.repository

import com.fsblaise.blizzchat.features.core.data.data_source.ConnectionApi
import com.fsblaise.blizzchat.features.core.domain.repository.ConnectionRepository
import javax.inject.Inject

class ConnectionRepositoryImpl @Inject constructor(
    private val connectionApi: ConnectionApi,
) : ConnectionRepository {
    override suspend fun hello(): Boolean {
        return try {
            connectionApi.hello()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}