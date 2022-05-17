package com.kuarkdijital.bitcoinTicker.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.kuarkdijital.bitcoinTicker.R
import com.kuarkdijital.bitcoinTicker.data.models.Coin
import com.kuarkdijital.bitcoinTicker.util.bindings.viewBindingWithBinder
import com.kuarkdijital.bitcoinTicker.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val binding by viewBindingWithBinder(MainFragmentBinding::bind)
    private lateinit var viewModel: MainViewModel
    private val coinList = ArrayList<Coin>()
    private var coinListAdapter = CoinListAdapter(coinList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rwCoinList.adapter = coinListAdapter

        viewModel.coinLiveData.observe(viewLifecycleOwner,{ coinList->
            coinListAdapter.updateData(coinList)
        })

        lifecycleScope.launchWhenStarted {
            viewModel.response.collect { event->
                when(event) {
                    is MainViewModel.ResponseEvent.Success-> {
                        binding.pbMain.isVisible = false
                    }
                    is MainViewModel.ResponseEvent.Failure-> {
                        binding.pbMain.isVisible = false

                    }
                    is MainViewModel.ResponseEvent.Loading-> {
                        binding.pbMain.isVisible = true
                    }
                }
            }
        }

        with(binding.scView){
            this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { viewModel.fetchCoinList(it) }
                    return true
                    //
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText.isNullOrEmpty()) {
                        coinListAdapter.updateData(ArrayList<Coin>())
                        binding.rwCoinList.removeAllViewsInLayout()
                    }
                    return false
                    //
                }
            })

            this.setOnCloseListener(object :SearchView.OnCloseListener{
                override fun onClose(): Boolean {
                    coinListAdapter.updateData(ArrayList<Coin>())
                    binding.rwCoinList.removeAllViewsInLayout()
                    return false
                }
            })
        }
    }
}