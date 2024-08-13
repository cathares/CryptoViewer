package com.cathares.cryptoviewer.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoAPI {

    @GET("coins/markets")
    suspend fun fetchTokens(
        @Query("vs_currency") currency: String,
        @Query("per_page") perPage: Int
    ): Response<ArrayList<TokenResponse>>

    @GET("coins/{id}")
    suspend fun fetchTokenInfo(
        @Path("id") name: String
    ): Response<TokenInfo>

}