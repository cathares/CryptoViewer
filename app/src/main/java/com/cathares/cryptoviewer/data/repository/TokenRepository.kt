package com.cathares.cryptoviewer.data.repository

import com.cathares.cryptoviewer.data.Token

interface TokenRepository {
    fun getTokens(): List<Token>
}