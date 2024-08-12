package com.cathares.cryptoviewer.data

import com.cathares.cryptoviewer.data.network.Description
import com.cathares.cryptoviewer.data.network.Image
import com.cathares.cryptoviewer.data.network.TokenInfo

data class TokenInfoUIState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val tokenInfo: TokenInfo? = null,
    val description: Description? = null,
    val image: Image? = null
) {
}