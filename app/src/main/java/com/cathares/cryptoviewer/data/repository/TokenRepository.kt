package com.cathares.cryptoviewer.data.repository

import com.cathares.cryptoviewer.data.network.NetworkResult
import com.cathares.cryptoviewer.data.network.TokenResponse

interface TokenRepository {
    suspend fun getTokens(currency: String): NetworkResult<List<TokenResponse>>
}