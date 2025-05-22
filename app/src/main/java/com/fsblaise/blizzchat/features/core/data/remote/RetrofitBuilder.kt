package com.fsblaise.blizzchat.features.core.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://api.example.com/"

    private fun getOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            // Here we can add more interceptors if needed
            // TODO: Token interceptor
            .build()
    }

    fun<T> createRetrofit(baseUrl: String? = null, endpoint: String, serviceClass: Class<T>): T {
        val finalBaseUrl = (baseUrl ?: BASE_URL).trimEnd('/') + endpoint

        return Retrofit.Builder()
            .baseUrl(finalBaseUrl)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(serviceClass)
    }
}