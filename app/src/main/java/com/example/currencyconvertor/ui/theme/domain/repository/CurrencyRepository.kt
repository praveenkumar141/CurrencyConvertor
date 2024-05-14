package com.example.currencyconvertor.ui.theme.domain.repository

import com.example.currencyconvertor.ui.theme.data.remote.CurrencyResponse

interface CurrencyRepository {
    suspend fun getLatestRates(): CurrencyResponse
}
