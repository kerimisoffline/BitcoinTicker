package com.kuarkdijital.bitcoinTicker.data

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
}