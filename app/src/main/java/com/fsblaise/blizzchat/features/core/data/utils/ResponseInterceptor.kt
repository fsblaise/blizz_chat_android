package com.fsblaise.blizzchat.features.core.data.utils

import okhttp3.Interceptor

class ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        val response = chain.proceed(request)
        println("--> Intercepting response for request: ${request.url}")
        println("Response code: ${response.code}")
        println("Response headers: ${response.headers}")
        println("Response body: ${response.body?.string()}")
        // Check if the response code indicates success
        if (response.code in 200..299) {
            println("Successful response: ${response.code}")
        } else {
            // Handle error responses
            val errorBody = response.body?.string() ?: "Unknown error"
            println("HTTP error: ${response.code}, $errorBody")
            // You can throw an exception or handle the error as needed
        }
        return response
    }
}