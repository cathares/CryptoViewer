package com.cathares.cryptoviewer.data.repository

import com.cathares.cryptoviewer.data.network.CryptoAPI
import com.cathares.cryptoviewer.data.network.NetworkResult
import com.cathares.cryptoviewer.data.network.TokenResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TokenRepositoryImpl(
    private val cryptoAPI: CryptoAPI,
    private val dispatcher: CoroutineDispatcher,
): TokenRepository {
    override suspend fun getTokens(currency: String): NetworkResult<List<TokenResponse>> {
        return withContext(dispatcher) {
            try {
                val response = cryptoAPI.fetchTokens(currency)
                if (response.isSuccessful) {
                    NetworkResult.Success(response.body()!!)
                } else {
                    NetworkResult.Error(response.code().toString())
                }
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Error")
            }
        }
    }
}