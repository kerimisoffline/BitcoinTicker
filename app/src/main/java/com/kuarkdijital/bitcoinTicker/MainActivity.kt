package com.kuarkdijital.bitcoinTicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import kotlinx.coroutines.flow.collect
import androidx.lifecycle.lifecycleScope
import com.kuarkdijital.bitcoinTicker.databinding.MainActivityBinding
import com.kuarkdijital.bitcoinTicker.ui.detail.DetailFragmet
import com.kuarkdijital.bitcoinTicker.ui.main.MainViewModel
import com.kuarkdijital.bitcoinTicker.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launchWhenStarted {
            viewModel.navType.collect { event->
                when(event) {
                    is MainViewModel.NavTypeEvent.Main-> {
                        val fragment = MainFragment()
                        Log.d("kerimDebug","navType Main")
                        fragment.arguments = event.bundle
                        navigateFragment(fragment)
                    }
                    is MainViewModel.NavTypeEvent.Detail-> {
                        val fragment = DetailFragmet()
                        Log.d("kerimDebug","navType Detail")
                        fragment.arguments = event.bundle
                        navigateFragment(fragment)
                    }
                    is MainViewModel.NavTypeEvent.Empty->{
                        Log.d("kerimDebug","navType Empty")
                    }
                    is MainViewModel.NavTypeEvent.Loading->{
                        Log.d("kerimDebug","navType Loading")
                    }
                }
            }
        }

        if (savedInstanceState == null) {
            navigateFragment(MainFragment.newInstance())
        }
    }

    private fun navigateFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitNow()
    }
}