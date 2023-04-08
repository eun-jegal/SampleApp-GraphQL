package com.example.sampleapp_graphql.data

import com.example.sampleapp_graphql.data.model.Country
import com.example.sampleapp_graphql.data.model.CountryDetail

interface CountryClient {
    suspend fun getCountries(): List<Country>
    suspend fun getCountryDetail(code: String): CountryDetail?
}