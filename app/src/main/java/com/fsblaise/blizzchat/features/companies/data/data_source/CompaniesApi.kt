package com.fsblaise.blizzchat.features.companies.data.data_source

import com.fsblaise.blizzchat.features.companies.domain.model.Company
import retrofit2.http.GET
import retrofit2.http.Path

interface CompaniesApi {
    @GET("init/{email}")
    suspend fun checkIfEmailInCompany(@Path("email") email: String): List<Company>
}