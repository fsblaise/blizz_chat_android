package com.fsblaise.blizzchat.features.companies.presentation.state

import com.fsblaise.blizzchat.features.companies.domain.model.Company

sealed interface CompaniesState {
    data object Initial : CompaniesState
    data object Fetching : CompaniesState
    data class Fetched(val companies: List<Company>) : CompaniesState
    data class Selected(
        val company: Company,
        val email: String
    ) : CompaniesState
    data class Error(val message: String) : CompaniesState
}
