package com.kuarkdijital.bitcoinTicker.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.kuarkdijital.bitcoinTicker.R
import com.kuarkdijital.bitcoinTicker.util.bindings.viewBindingWithBinder
import com.kuarkdijital.bitcoinTicker.databinding.DetailFragmetBinding

class DetailFragmet : Fragment(R.layout.detail_fragmet) {

    companion object {
        fun newInstance() = DetailFragmet()
    }

    private val binding by viewBindingWithBinder(DetailFragmetBinding::bind)
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}