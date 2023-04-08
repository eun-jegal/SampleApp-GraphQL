package com.example.sampleapp_graphql

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sampleapp_graphql.ui.CountriesScreen
import com.example.sampleapp_graphql.ui.CountriesViewModel
import com.example.sampleapp_graphql.ui.theme.SampleAppGraphQLTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var viewModel: CountriesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleAppGraphQLTheme {
                val viewModel = hiltViewModel<CountriesViewModel>()
                val state by viewModel.state.collectAsState()
                CountriesScreen(
                    state = state,
                    onSelectCountry = { viewModel.selectCountry(it) },
                    onDismissDialog = { viewModel.dismissCountryDialog() }
                )
            }
        }
    }
}
