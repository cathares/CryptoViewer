package com.cathares.cryptoviewer.di

import com.cathares.cryptoviewer.data.CryptoAPI
import com.cathares.cryptoviewer.data.repository.TokenRepository
import com.cathares.cryptoviewer.data.repository.TokenRepositoryImpl
import com.cathares.cryptoviewer.ui.viemodel.TokenListViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.Retrofit

private val json = Json { ignoreUnknownKeys = true }

val appModules = module {
    single<TokenRepository> { TokenRepositoryImpl(get(), get()) }
    single { Dispatchers.IO }
    single { TokenListViewModel(get()) }
    single {
        Retrofit.Builder()
            .addConverterFactory(
                json.asConverterFactory(contentType = "application/json".toMediaType())

            )
            .baseUrl("https://api.coingecko.com/api/v3/")
            .build()
    }
    single { get<Retrofit>().create(CryptoAPI::class.java) }
}