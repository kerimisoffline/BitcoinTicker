package com.kuarkdijital.bitcoinTicker.data

import com.kuarkdijital.bitcoinTicker.data.models.CoinDetail
import com.kuarkdijital.bitcoinTicker.data.models.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BaseApi {

    @GET("search")
    suspend fun getSearchResponse(
        @Query("query") query:String
    ): Response<SearchResponse>

    @GET("coins/{id}")
    suspend fun getCoinDetail(
        @Path("id") id:String,
        @Query("localization") localization:Boolean=false,
        @Query("tickers") tickers:Boolean=false,
        @Query("market_data") market_data:Boolean=false,
        @Query("community_data") community_data:Boolean=false,
        @Query("developer_data") developer_data:Boolean=false,
        @Query("sparkline") sparkline:Boolean=false
    ): Response<CoinDetail>
}