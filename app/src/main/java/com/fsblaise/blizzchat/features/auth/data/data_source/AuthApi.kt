package com.fsblaise.blizzchat.features.auth.data.data_source

import com.fsblaise.blizzchat.features.auth.data.data_source.dto.AuthResponseDto
import com.fsblaise.blizzchat.features.auth.data.data_source.dto.SignInDto
import com.fsblaise.blizzchat.features.auth.data.data_source.dto.SignUpDto
import com.fsblaise.blizzchat.features.auth.domain.model.UserProfile
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthApi {
    @GET(".")
    suspend fun fetchUserByToken(): UserProfile

    @POST("signIn")
    suspend fun signIn(@Body body: SignInDto): AuthResponseDto

    @POST("signUp")
    suspend fun signUp(@Body body: SignUpDto): AuthResponseDto

    @PATCH(".")
    suspend fun update(@Body body: UserProfile): UserProfile
}