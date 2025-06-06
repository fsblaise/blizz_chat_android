package com.fsblaise.blizzchat.features.companies.data.repository

import com.fsblaise.blizzchat.BuildConfig
import com.fsblaise.blizzchat.features.companies.data.data_source.CompaniesApi
import com.fsblaise.blizzchat.features.companies.domain.model.Company
import com.fsblaise.blizzchat.features.companies.domain.repository.CompaniesRepository
import com.fsblaise.blizzchat.features.core.domain.repository.SessionManagerRepository
import javax.inject.Inject

class CompaniesRepositoryImpl @Inject constructor(
    private val companiesApi: CompaniesApi,
    private val sessionManagerRepository: SessionManagerRepository,
) : CompaniesRepository {

    override suspend fun checkIfEmailInCompany(email: String): List<Company>? {
        return try {
            val response = companiesApi.checkIfEmailInCompany(email)
            if (response.isEmpty()) {
                println("saving session with no company (empty list)")
                sessionManagerRepository.saveSession(email)
                sessionManagerRepository.setActiveSession(email)
                null
            } else {
                response
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println("saving session with no company")
            sessionManagerRepository.saveSession(email)
            sessionManagerRepository.setActiveSession(email)
            null
        }
    }

    override fun selectCompany(
        company: Company,
        email: String
    ) {
        sessionManagerRepository.saveSession(email, company.apiUrl, company.name)
        sessionManagerRepository.setActiveSession(email, company.apiUrl)
    }

    override fun getApiUrl(): String {
        val session = sessionManagerRepository.getActiveSession()
        return session?.apiUrl ?: BuildConfig.API_URL
    }
}