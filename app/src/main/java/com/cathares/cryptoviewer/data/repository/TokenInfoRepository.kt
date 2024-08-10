package com.cathares.cryptoviewer.data.repository

import com.cathares.cryptoviewer.data.TokenInfo

interface TokenInfoRepository {
    fun getTokenInfo(): TokenInfo
}