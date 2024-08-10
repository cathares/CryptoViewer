package com.cathares.cryptoviewer.ui.viemodel

import androidx.lifecycle.ViewModel
import com.cathares.cryptoviewer.data.repository.TokenInfoRepository
import com.cathares.cryptoviewer.data.repository.TokenInfoRepositoryImpl

class TokenInfoViewModel: ViewModel() {
    private val tokenInfoRepository: TokenInfoRepository = TokenInfoRepositoryImpl()
    fun getTokenInfo() = tokenInfoRepository.getTokenInfo()
}