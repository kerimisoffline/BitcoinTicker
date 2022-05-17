package com.kuarkdijital.bitcoinTicker.main

import com.kuarkdijital.bitcoinTicker.data.models.CoinDetail
import com.kuarkdijital.bitcoinTicker.data.models.SearchResponse
import com.kuarkdijital.bitcoinTicker.util.Resource

interface MainRepository {

    suspend fun getRates(query:String) : Resource<SearchResponse>

    suspend fun getCoinDetail(id:String) : Resource<CoinDetail>

}