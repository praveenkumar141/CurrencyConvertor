package com.example.currencyconvertor.ui.theme.di
import com.example.currencyconvertor.ui.theme.data.remote.CurrencyApi
import com.example.currencyconvertor.ui.theme.data.repository.CurrencyRepositoryImpl
import com.example.currencyconvertor.ui.theme.domain.repository.CurrencyRepository
import com.example.currencyconvertor.ui.theme.presentation.CurrencyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    factory { CurrencyApi() }
    factory<CurrencyRepository> { CurrencyRepositoryImpl(currencyApi = get()) }
    viewModel { CurrencyViewModel(currencyRepository = get()) }
}
