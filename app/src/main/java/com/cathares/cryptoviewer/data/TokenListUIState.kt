package com.cathares.cryptoviewer.data

data class TokenListUIState(
    val isLoading: Boolean = false,
    val tokens: List<TokenResponse> = emptyList(),
    val error: String? = null,
    val chipSelected: Boolean = true
)