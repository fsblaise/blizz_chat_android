package com.fsblaise.blizzchat.features.core.data.utils

import com.fsblaise.blizzchat.features.core.domain.repository.SessionManagerRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val sessionManagerRepository: SessionManagerRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sessionManagerRepository.getActiveSession()?.token
        println("Intercepting request with token: $token")
        val request = if (token != null) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }
        return chain.proceed(request)
    }
}