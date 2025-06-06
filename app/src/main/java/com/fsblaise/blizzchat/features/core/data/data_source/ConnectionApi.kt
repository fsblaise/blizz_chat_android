package com.fsblaise.blizzchat.features.core.data.data_source

import retrofit2.http.GET

interface ConnectionApi {
    @GET("hello")
    suspend fun hello()
}