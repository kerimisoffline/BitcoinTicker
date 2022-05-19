package com.kuarkdijital.bitcoinTicker.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.kuarkdijital.bitcoinTicker.data.models.Coin
import com.kuarkdijital.bitcoinTicker.databinding.ItemCoinListBinding
import com.kuarkdijital.bitcoinTicker.util.constant.Extra
import com.kuarkdijital.bitcoinTicker.util.constant.Navigate


class CoinListAdapter(private val viewModel: MainViewModel,private var coinList: List<Coin>) :
    RecyclerView.Adapter<CoinListAdapter.CoinListViewHolder>() {

    class CoinListViewHolder(val binding: ItemCoinListBinding) : RecyclerView.ViewHolder(binding.root) {
        //val button = binding.
    }

    fun updateData(data: List<Coin>){
        this.coinList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        val binding = ItemCoinListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CoinListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        with(coinList[position]){
            val coin = this
            with(holder){
                binding.txtCoinListItem.text = coin.name
                binding.itemCoinRoot.setOnClickListener {
                    viewModel.navigateView(Navigate.Detail,
                        bundleOf(Extra.coinId to coin.id)
                    )
                }
            }
        }
    }
}