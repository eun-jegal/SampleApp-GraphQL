package com.example.sampleapp_graphql.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.sampleapp_graphql.data.model.Country
import com.example.sampleapp_graphql.data.model.CountryDetail

@Composable
fun CountriesScreen(
    state: CountriesViewModel.CountriesState,
    onSelectCountry: (String) -> Unit,
    onDismissDialog: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.countries) { country ->
                    CountryItem(country = country,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelectCountry(country.code) }
                            .padding(16.dp))

                }
            }
        }
        if (state.selectedCountry != null) {
            CountryDialog(
                country = state.selectedCountry,
                onDismissDialog = { onDismissDialog },
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.White)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
private fun CountryItem(
    country: Country,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = country.name, fontSize = 24.sp)
        Text(text = country.emoji, fontSize = 30.sp)
    }
}

@Composable
private fun CountryDialog(
    country: CountryDetail,
    onDismissDialog: () -> Unit,
    modifier: Modifier = Modifier
) {
    val joinedLanguages = ""
//        remember(country.languages) {
//        country.languages.joinToString()
//    }
    Dialog(onDismissRequest = { onDismissDialog() }) {
        Column(modifier = modifier) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = country.name, fontSize = 24.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = country.emoji, fontSize = 30.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Continent: ${country.continent}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Currency: ${country.currency}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Language(s): $joinedLanguages", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}