package com.cathares.cryptoviewer.data.repository

import com.cathares.cryptoviewer.data.network.CryptoAPI
import com.cathares.cryptoviewer.data.network.NetworkResult
import com.cathares.cryptoviewer.data.network.TokenInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TokenInfoRepositoryImpl(
    private val cryptoAPI: CryptoAPI,
    private val dispatcher: CoroutineDispatcher
): TokenInfoRepository {
    override suspend fun getTokenInfo(name: String): NetworkResult<TokenInfo> {
        return withContext(dispatcher) {
            try {
                val response = cryptoAPI.fetchTokenInfo(name)
                if (response.isSuccessful) {
                    NetworkResult.Success(response.body()!!)
                } else {
                    NetworkResult.Error(response.code().
                    toString())
                }
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Error")
            }
        }
    }
}