package com.cathares.cryptoviewer.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    @SerialName("id")
    val id: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("name")
    val name: String,
    @SerialName("image")
    val image: String,
    @SerialName("current_price")
    val currentPrice: Double,
    @SerialName("price_change_percentage_24h")
    val priceChangePercentage24h: Double,
)