package com.cathares.cryptoviewer.ui.state

import com.cathares.cryptoviewer.data.network.TokenResponse

data class TokenListUIState(
    val isLoading: Boolean = false,
    val tokens: List<TokenResponse> = emptyList(),
    val error: String? = null,
    val chipSelected: Boolean = true
)