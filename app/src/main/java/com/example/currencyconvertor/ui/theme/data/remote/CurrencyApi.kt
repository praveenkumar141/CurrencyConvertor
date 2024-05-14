package com.example.currencyconvertor.ui.theme.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {
    @GET("latest.json?app_id=5bba05098c994346b48b361dc46d7fbc")
    suspend fun getLatestRates(): CurrencyResponse
}

class CurrencyApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://openexchangerates.org/api/")
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(
            KotlinJsonAdapterFactory()
        ).build()))
        .build()

    private val service = retrofit.create(CurrencyApiService::class.java)

    suspend fun getLatestRates(): CurrencyResponse {
        return service.getLatestRates()
    }
}