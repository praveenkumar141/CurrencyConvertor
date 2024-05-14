package com.example.currencyconvertor.ui.theme.data.repository

import com.example.currencyconvertor.ui.theme.data.remote.CurrencyApi
import com.example.currencyconvertor.ui.theme.data.remote.CurrencyResponse
import com.example.currencyconvertor.ui.theme.domain.repository.CurrencyRepository

class CurrencyRepositoryImpl(
    private val currencyApi: CurrencyApi
) : CurrencyRepository {
    override suspend fun getLatestRates(): CurrencyResponse {
        return currencyApi.getLatestRates()
    }
}