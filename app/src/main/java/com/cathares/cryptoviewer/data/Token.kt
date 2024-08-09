package com.cathares.cryptoviewer.data

data class Token(
    val name: String,
    val ticker: String,
    val image: String,
    val price: Float,
    val priceChangePercentage: Float,
    val currency: String)
