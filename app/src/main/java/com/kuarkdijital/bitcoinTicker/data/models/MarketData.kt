package com.kuarkdijital.bitcoinTicker.data.models

data class MarketData(
    val circulating_supply: Double,
    val current_price: CurrentPrice?,
    val fdv_to_tvl_ratio: Any,
    val last_updated: String,
    val market_cap_change_24h: Double,
    val market_cap_change_percentage_24h: Double,
    val market_cap_rank: Int,
    val max_supply: Int,
    val mcap_to_tvl_ratio: Any,
    val price_change_24h: Double,
    val price_change_24h_in_currency: PriceChange24hİnCurrency,
    val price_change_percentage_14d: Double,
    val price_change_percentage_1y: Int,
    val price_change_percentage_200d: Double,
    val price_change_percentage_24h: Double?,
    val price_change_percentage_24h_in_currency: PriceChangePercentage24hİnCurrency,
    val price_change_percentage_30d: Double,
    val price_change_percentage_60d: Double,
    val price_change_percentage_7d: Double,
    val roi: Any,
    val total_supply: Int,
    val total_value_locked: Any
)