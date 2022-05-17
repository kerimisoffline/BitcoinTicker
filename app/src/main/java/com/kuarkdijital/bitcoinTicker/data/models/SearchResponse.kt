package com.kuarkdijital.bitcoinTicker.data.models

data class SearchResponse(
    val categories: List<Category>,
    val coins: List<Coin>,
    val exchanges: List<Any>,
    val icos: List<Any>,
    val nfts: List<Nft>
)