package com.kuarkdijital.bitcoinTicker.data.models

data class Coin(
    val id: String,
    val large: String,
    val market_cap_rank: Int,
    val name: String,
    val symbol: String,
    val thumb: String
)