package com.kuarkdijital.bitcoinTicker.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuarkdijital.bitcoinTicker.data.models.CoinDetail
import com.kuarkdijital.bitcoinTicker.data.repo.MainRepository
import com.kuarkdijital.bitcoinTicker.ui.main.MainViewModel
import com.kuarkdijital.bitcoinTicker.util.DispactherProvider
import com.kuarkdijital.bitcoinTicker.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MainRepository,
    private val dispatcher: DispactherProvider
) : ViewModel() {
    var coinDetailLiveData = MutableLiveData<CoinDetail>()
    private val _response = MutableStateFlow<MainViewModel.ResponseEvent>(MainViewModel.ResponseEvent.Empty)
    val response: StateFlow<MainViewModel.ResponseEvent> = _response

    sealed class ResponseEvent {
        class Success(val resultData: Any) : ResponseEvent()
        class Failure(val errorText:String) : ResponseEvent()
        object Loading : ResponseEvent()
        object Empty : ResponseEvent()
    }

    fun fetchCoinDetail(
        id:String
    ) {
        viewModelScope.launch(dispatcher.io) {
            _response.value = MainViewModel.ResponseEvent.Loading
            when(val coinDetailResponse = repository.getCoinDetail(id)){
                is Resource.Success -> {
                    coinDetailLiveData.postValue(coinDetailResponse.data!!)
                    _response.value = MainViewModel.ResponseEvent.Success("success")
                }
                is Resource.Error -> {
                    Log.d("kerimDebug","error")
                    _response.value = MainViewModel.ResponseEvent.Failure(coinDetailResponse.message!!)
                }
            }
        }
    }
}