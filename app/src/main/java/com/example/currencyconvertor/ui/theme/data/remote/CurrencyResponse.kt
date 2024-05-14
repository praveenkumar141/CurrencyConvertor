package com.example.currencyconvertor.ui.theme.data.remote

data class CurrencyResponse(
    val disclaimer: String? = null,
    val license: String? = null,
    val timestamp: Long? = null,
    val base: String? = null,
    val rates: Map<String, Double>? = null
)
