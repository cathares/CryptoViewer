package com.cathares.cryptoviewer.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenInfo(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("image")
    val image: Image,
    @SerialName("description")
    val description: Description,
    @SerialName("categories")
    val categories: List<String>
)

@Serializable
data class Image(
    @SerialName("large")
    val largeImage: String
)
@Serializable
data class Description(
    @SerialName("en")
    val description: String
)