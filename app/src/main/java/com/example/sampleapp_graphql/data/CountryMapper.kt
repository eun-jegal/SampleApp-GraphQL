package com.example.sampleapp_graphql.data

import com.example.CountriesQuery
import com.example.CountryQuery
import com.example.sampleapp_graphql.data.model.Country
import com.example.sampleapp_graphql.data.model.CountryDetail

fun CountriesQuery.Country.toCountry(): Country {
    return Country(
        code = code ?: "",
        name = name ?: "",
        emoji = emoji ?: "",
        languages = languages?.mapNotNull { it?.name } ?: emptyList()
    )
}

fun CountryQuery.Country.toCountryDetail(): CountryDetail {
    return CountryDetail(
        code = code ?: "",
        name = name ?: "",
        emoji = emoji ?: "",
        currency = currency ?: "No currency",
        continent = continent?.name ?: ""
    )
}