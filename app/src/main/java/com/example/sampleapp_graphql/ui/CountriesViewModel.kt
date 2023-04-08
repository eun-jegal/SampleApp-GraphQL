package com.example.sampleapp_graphql.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp_graphql.data.CountryClient
import com.example.sampleapp_graphql.data.model.Country
import com.example.sampleapp_graphql.data.model.CountryDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countryClient: CountryClient
) : ViewModel() {

    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    data class CountriesState(
        val countries: List<Country> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: CountryDetail? = null
    )

    init {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }
            _state.update { it.copy(
                countries = countryClient.getCountries(),
                isLoading = false
            ) }
        }
    }

    fun selectCountry(code: String) {
        viewModelScope.launch {
            _state.update { it.copy(
                selectedCountry = countryClient.getCountryDetail(code)
            ) }
        }
    }

    fun dismissCountryDialog() {
        viewModelScope.launch {
            _state.update { it.copy(
                selectedCountry = null
            ) }
        }
    }
}