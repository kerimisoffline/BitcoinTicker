package com.kuarkdijital.bitcoinTicker.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.kuarkdijital.bitcoinTicker.R
import com.kuarkdijital.bitcoinTicker.databinding.DetailFragmetBinding
import com.kuarkdijital.bitcoinTicker.util.constant.Extra

class DetailFragmet : Fragment(R.layout.detail_fragmet) {

    companion object {
        @JvmStatic
        fun newInstance() = DetailFragmet()
    }

    private var _binding : DetailFragmetBinding?=null
    private val binding get() = _binding!!

    private val viewModel : DetailViewModel by activityViewModels()
    private var coinId : String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            coinId = it.getString(Extra.coinId)
        }
        Log.d("kerimDebug","${coinId}")
        coinId?.let {
            viewModel.fetchCoinDetail(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailFragmetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.coinDetailLiveData.observe(viewLifecycleOwner,{ coinDetail->
            with(binding){
                if(coinDetail.hashing_algorithm is String){
                    txtDetailHashing.text = coinDetail.hashing_algorithm
                }
                txtDetailDescription.text = coinDetail.description.en
                if(coinDetail.market_data!=null){
                    if(coinDetail.market_data.current_price!=null){
                        txtDetailCurrentPrice.text = "${coinDetail.market_data.current_price.usd}"
                    }
                    if(coinDetail.market_data.price_change_percentage_24h!=null){
                        txtDetailLastVolume.text = "${coinDetail.market_data.price_change_percentage_24h}"
                    }
                }
            }
        })
    }

}