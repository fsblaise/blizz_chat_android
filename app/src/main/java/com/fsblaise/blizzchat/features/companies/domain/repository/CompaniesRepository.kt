package com.fsblaise.blizzchat.features.companies.domain.repository

import com.fsblaise.blizzchat.features.companies.domain.model.Company

interface CompaniesRepository {
    suspend fun checkIfEmailInCompany(email: String): List<Company>?
    fun selectCompany(company: Company, email: String)
    fun getApiUrl(): String
}