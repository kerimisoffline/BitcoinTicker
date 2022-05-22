package com.kuarkdijital.bitcoinTicker.data.models

data class CoinDetail(
    val description: Description,
    val hashing_algorithm: Any,
    val id: String,
    val image: Image,
    val market_data: MarketData?,
    val name: String
)