package com.cathares.cryptoviewer.data.repository

import com.cathares.cryptoviewer.data.CryptoAPI
import com.cathares.cryptoviewer.data.NetworkResult
import com.cathares.cryptoviewer.data.TokenResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TokenRepositoryImpl(
    private val cryptoAPI: CryptoAPI,
    private val dispatcher: CoroutineDispatcher
): TokenRepository {
    override suspend fun getTokens(): NetworkResult<List<TokenResponse>> {
        return withContext(dispatcher) {
            try {
                val response = cryptoAPI.fetchTokens("usd")
                if (response.isSuccessful) {
                    NetworkResult.Success(response.body()!!)
                } else {
                    NetworkResult.Error(response.errorBody().toString())
                }
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Error")
            }
        }
    }
}