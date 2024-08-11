package com.cathares.cryptoviewer.data

import android.icu.util.Currency
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoAPI {

    @GET("coins/markets")
    suspend fun fetchTokens(
        @Query("vs_currency") currency: String
    ): Response<ArrayList<TokenResponse>>

}