package com.cathares.cryptoviewer.data.repository

import com.cathares.cryptoviewer.data.NetworkResult
import com.cathares.cryptoviewer.data.TokenResponse

interface TokenRepository {
    suspend fun getTokens(currency: String): NetworkResult<List<TokenResponse>>
}