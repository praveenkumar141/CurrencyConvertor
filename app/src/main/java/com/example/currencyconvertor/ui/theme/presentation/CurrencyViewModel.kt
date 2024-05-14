package com.example.currencyconvertor.ui.theme.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconvertor.ui.theme.data.remote.CurrencyResponse
import com.example.currencyconvertor.ui.theme.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CurrencyViewModel(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {
    private lateinit var latestRates: CurrencyResponse

    private val _currencyData = MutableStateFlow(CurrencyResponse())
    val currencyData: StateFlow<CurrencyResponse>
        get() = _currencyData.asStateFlow()

    fun fetchLatestRates() {
        viewModelScope.launch {
            latestRates = currencyRepository.getLatestRates()
            _currencyData.updateValue { latestRates }
        }
    }
}

private inline fun <T> MutableStateFlow<T>.updateValue(block: T.() -> T) {
    value = value.block()
}