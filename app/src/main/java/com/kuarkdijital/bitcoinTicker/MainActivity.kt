package com.kuarkdijital.bitcoinTicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import kotlinx.coroutines.flow.collect
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
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
        //setupActionBarWithNavController(findNavController(R.id.defaultFragment))
        Navigation.findNavController(this,R.id.defaultFragment).addOnDestinationChangedListener {controller,destination,b->
            updateToolBar(destination.id,b)
        }
        lifecycleScope.launchWhenStarted {
            viewModel.navType.collect { event->
                when(event) {
                    is MainViewModel.NavTypeEvent.Main-> {
                        findNavController(R.id.defaultFragment).navigate(R.id.action_main,event.bundle)
                    }
                    is MainViewModel.NavTypeEvent.Detail-> {
                        findNavController(R.id.defaultFragment).navigate(R.id.action_detail,event.bundle)
                    }
                    is MainViewModel.NavTypeEvent.Empty->{
                        //
                    }
                    is MainViewModel.NavTypeEvent.Loading->{
                        //
                    }
                }
            }
        }

        /*
        if (savedInstanceState == null) {
            Navigation.findNavController(this@MainActivity,R.id.defaultFragment).navigate(R.id.action_main)
        }
         */
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.defaultFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun updateToolBar(id:Int,bundle: Bundle?){

    }
}