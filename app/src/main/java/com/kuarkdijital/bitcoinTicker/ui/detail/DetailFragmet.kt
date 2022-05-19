package com.kuarkdijital.bitcoinTicker.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import com.kuarkdijital.bitcoinTicker.R
import com.kuarkdijital.bitcoinTicker.util.bindings.viewBindingWithBinder
import com.kuarkdijital.bitcoinTicker.databinding.DetailFragmetBinding
import com.kuarkdijital.bitcoinTicker.util.constant.Extra

class DetailFragmet : Fragment(R.layout.detail_fragmet) {

    companion object {
        @JvmStatic
        fun newInstance() = DetailFragmet()
    }

    private val binding by viewBindingWithBinder(DetailFragmetBinding::bind)
    private val viewModel : DetailViewModel by activityViewModels()
    private var coinId : String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            coinId = it.getString(Extra.coinId)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("kerimDebug","coin Id = ${coinId}")
    }

}