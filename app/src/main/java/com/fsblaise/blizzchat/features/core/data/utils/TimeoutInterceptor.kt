package com.fsblaise.blizzchat.features.core.data.utils

import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

/// Interceptor that sets a shorter timeout for requests to the /init/ endpoint.
class TimeoutInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (request.url.encodedPath.contains("/init/")) {
            return chain.withConnectTimeout(3, TimeUnit.SECONDS)
                .withReadTimeout(3, TimeUnit.SECONDS)
                .withWriteTimeout(3, TimeUnit.SECONDS)
                .proceed(request)
        }

        return chain.proceed(request)
    }
}