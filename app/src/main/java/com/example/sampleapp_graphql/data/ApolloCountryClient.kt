package com.example.sampleapp_graphql.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.example.CountriesQuery
import com.example.CountryQuery
import com.example.sampleapp_graphql.data.model.Country
import com.example.sampleapp_graphql.data.model.CountryDetail

class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient {
    override suspend fun getCountries(): List<Country> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries?.mapNotNull { it?.toCountry() }
            ?: emptyList()
    }

    override suspend fun getCountryDetail(code: String): CountryDetail? {
        return apolloClient
            .query(CountryQuery(Optional.present(code)))
            .execute()
            .data
            ?.country
            ?.toCountryDetail()
    }
}