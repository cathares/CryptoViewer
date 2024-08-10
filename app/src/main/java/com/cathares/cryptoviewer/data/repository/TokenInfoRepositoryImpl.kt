package com.cathares.cryptoviewer.data.repository

import com.cathares.cryptoviewer.data.TokenInfo

class TokenInfoRepositoryImpl: TokenInfoRepository {
    override fun getTokenInfo(): TokenInfo {
        return TokenInfo("tmp", "some Description", "some Categories" )
    }
}