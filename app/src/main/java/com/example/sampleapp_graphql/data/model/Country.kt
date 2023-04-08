package com.example.sampleapp_graphql.data.model

data class Country(
    val code: String,
    val name: String,
    val emoji: String,
    val languages: List<String>
)