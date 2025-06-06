package com.fsblaise.blizzchat.features.companies.presentation.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fsblaise.blizzchat.features.companies.domain.model.Company
import com.fsblaise.blizzchat.features.companies.domain.repository.CompaniesRepository
import com.fsblaise.blizzchat.features.core.domain.repository.ConnectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class CompaniesViewModel @Inject constructor(
    private val repository: CompaniesRepository,
    private val connectionRepository: ConnectionRepository
) : ViewModel() {
    private val _state = MutableStateFlow<CompaniesState>(CompaniesState.Initial)
    open val state: StateFlow<CompaniesState> = _state

    fun checkIfEmailInCompany(email: String) {
        _state.value = CompaniesState.Fetching
        viewModelScope.launch {
            try {
                val companies = repository.checkIfEmailInCompany(email)
                if (companies.isNullOrEmpty()) {
                    _state.value = CompaniesState.Fetched(emptyList())
                } else {
                    _state.value = CompaniesState.Fetched(companies)
                }
            } catch (e: Exception) {
                _state.value = CompaniesState.Error(e.message ?: "An error occurred")
            }
        }
    }

    fun selectCompany(company: Company, email: String) {
        _state.value = CompaniesState.Fetching
        viewModelScope.launch {
            try {
                val response = connectionRepository.hello()
                if (response) {
                    repository.selectCompany(company, email)
                    _state.value = CompaniesState.Selected(company, email)
                } else {
                    _state.value = CompaniesState.Error("Failed to connect to the server")
                }
            } catch (e: Exception) {
                _state.value = CompaniesState.Error(e.message ?: "An error occurred")
            }
        }
    }
}