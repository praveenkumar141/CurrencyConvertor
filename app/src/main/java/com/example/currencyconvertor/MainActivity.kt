package com.example.currencyconvertor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.currencyconvertor.ui.theme.di.appModule
import com.example.currencyconvertor.ui.theme.presentation.CurrencyConvertorParent
import com.example.currencyconvertor.ui.theme.presentation.CurrencyViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }
        val viewModel: CurrencyViewModel by inject()
        viewModel.fetchLatestRates()
        setContent {
            val state = viewModel.currencyData.collectAsState().value
            CurrencyConvertorParent(state)
        }
    }
}

