package com.cathares.cryptoviewer.data.repository

import com.cathares.cryptoviewer.data.network.NetworkResult
import com.cathares.cryptoviewer.data.network.TokenInfo

interface TokenInfoRepository {
    suspend fun getTokenInfo(name: String): NetworkResult<TokenInfo>
}