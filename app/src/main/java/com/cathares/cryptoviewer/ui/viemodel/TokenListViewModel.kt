package com.cathares.cryptoviewer.ui.viemodel

import androidx.lifecycle.ViewModel
import com.cathares.cryptoviewer.data.repository.TokenRepository
import com.cathares.cryptoviewer.data.repository.TokenRepositoryImpl


class TokenListViewModel: ViewModel() {
    private val tokenRepository: TokenRepository = TokenRepositoryImpl()

    fun getTokens() = tokenRepository.getTokens()
}