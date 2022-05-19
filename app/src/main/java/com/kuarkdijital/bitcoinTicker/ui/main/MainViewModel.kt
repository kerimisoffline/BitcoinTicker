package com.kuarkdijital.bitcoinTicker.ui.main

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuarkdijital.bitcoinTicker.data.models.Coin
import com.kuarkdijital.bitcoinTicker.data.models.CoinDetail
import com.kuarkdijital.bitcoinTicker.main.MainRepository
import com.kuarkdijital.bitcoinTicker.util.DispactherProvider
import com.kuarkdijital.bitcoinTicker.util.Resource
import com.kuarkdijital.bitcoinTicker.util.constant.Navigate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    private val dispatcher: DispactherProvider
): ViewModel() {

    var coinLiveData = MutableLiveData<List<Coin>>()
    var coinDetailLiveData = MutableLiveData<CoinDetail>()
    private val _navType = MutableStateFlow<NavTypeEvent>(NavTypeEvent.Empty)
    val navType: StateFlow<NavTypeEvent> = _navType
    private val _response = MutableStateFlow<ResponseEvent>(ResponseEvent.Empty)
    val response: StateFlow<ResponseEvent> = _response

    sealed class ResponseEvent {
        class Success(val resultData: Any) : ResponseEvent()
        class Failure(val errorText:String) : ResponseEvent()
        object Loading : ResponseEvent()
        object Empty : ResponseEvent()
    }

    sealed class NavTypeEvent {
        class Main(val bundle: Bundle?) : NavTypeEvent()
        class Detail(val bundle: Bundle?) : NavTypeEvent()
        object Loading : NavTypeEvent()
        object Empty : NavTypeEvent()
    }

    fun fetchCoinList(
        query:String
    ) {
        viewModelScope.launch(dispatcher.io) {
            _response.value = ResponseEvent.Loading
            when(val coinListResponse = repository.getRates(query)){
                is Resource.Success -> {
                    coinLiveData.postValue(coinListResponse.data!!.coins)
                    _response.value = ResponseEvent.Success("success")
                }
                is Resource.Error -> {
                    _response.value = ResponseEvent.Failure(coinListResponse.message!!)
                }
            }
        }
    }

    fun fetchCoinDetail(
        id:String
    ) {
        viewModelScope.launch(dispatcher.io) {
            _response.value = ResponseEvent.Loading
            when(val coinDetailResponse = repository.getCoinDetail(id)){
                is Resource.Success -> {
                    coinDetailLiveData.postValue(coinDetailResponse.data!!)
                    _response.value = ResponseEvent.Success("success")
                }
                is Resource.Error -> {
                    _response.value = ResponseEvent.Failure(coinDetailResponse.message!!)
                }
            }
        }
    }

    fun navigateView(
        nav: String,
        bundle: Bundle?
    ) {
        viewModelScope.launch(dispatcher.io) {
            _navType.value = NavTypeEvent.Loading
            when(nav){
                Navigate.Main -> {
                    _navType.value = NavTypeEvent.Main(bundle = bundle)
                }
                Navigate.Detail -> {
                    _navType.value = NavTypeEvent.Detail(bundle = bundle)
                }
            }
        }
    }
}