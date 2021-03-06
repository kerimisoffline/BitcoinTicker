package com.kuarkdijital.bitcoinTicker.data.repo

import com.kuarkdijital.bitcoinTicker.data.BaseApi
import com.kuarkdijital.bitcoinTicker.data.models.CoinDetail
import com.kuarkdijital.bitcoinTicker.data.models.SearchResponse
import com.kuarkdijital.bitcoinTicker.util.Resource
import java.lang.Exception
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val api : BaseApi
) : MainRepository {

    override suspend fun getRates(query:String): Resource<SearchResponse> {
        return try {
            val response = api.getSearchResponse(query)
            val result = response.body()
            if(response.isSuccessful && result != null){
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception){
            Resource.Error(e.message ?: "An error occured.")
        }
    }

    override suspend fun getCoinDetail(id:String): Resource<CoinDetail> {
        return try {
            val response = api.getCoinDetail(id)
            val result = response.body()
            if(response.isSuccessful && result != null){
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception){
            Resource.Error(e.message ?: "An error occured.")
        }
    }
}