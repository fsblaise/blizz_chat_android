package com.fsblaise.blizzchat.features.core.data.remote

import com.fsblaise.blizzchat.BuildConfig
import com.fsblaise.blizzchat.features.core.data.utils.ResponseInterceptor
import com.fsblaise.blizzchat.features.core.data.utils.TimeoutInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.time.Duration

object RetrofitBuilder {
    private const val BASE_URL = BuildConfig.API_URL

    private fun getOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
//            level = HttpLoggingInterceptor.Level.HEADERS
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .callTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(TimeoutInterceptor())
//            .addInterceptor(ResponseInterceptor())
            .build()
    }

    fun<T> createRetrofit(baseUrl: String? = null, endpoint: String, serviceClass: Class<T>, interceptor: Interceptor): T {
        val finalBaseUrl = (baseUrl ?: BASE_URL).trimEnd('/') + endpoint

        return Retrofit.Builder()
            .baseUrl(finalBaseUrl)
            .client(getOkHttpClient(interceptor))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(serviceClass)
    }
}