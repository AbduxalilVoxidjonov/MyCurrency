package com.example.currency

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.currency.databinding.ActivityMainBinding
import com.example.currency.Main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import com.example.currency.Data.Models.ExchangeResponse
import com.example.currency.Utils.ConvertEvent
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "TAG"
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.convertBtn.setOnClickListener {
            val fromCurrency = binding.fromSpinner.selectedItem as String
            val toCurrency = binding.toSpinner.selectedItem as String
            val amountString = binding.amountEt.text.toString()
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(binding.amountEt.windowToken, 0)
            viewModel.getConvertRate(
                fromCurrency.substring(0, 3),
                toCurrency.substring(0, 3),
                amountString
            )
        }


        lifecycleScope.launchWhenCreated {
            viewModel.conversion.collectLatest {
                when (it) {
                    ConvertEvent.Empty -> Unit
                    is ConvertEvent.Error -> {
                        Log.d(TAG, "onCreate: ${it.errorMessage}")
                        binding.progressBar.isVisible = false
                    }

                    ConvertEvent.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is ConvertEvent.Success -> {
                        binding.progressBar.isVisible = false
                        Log.d(TAG, "onCreate: ${it.result}")
                        binding.resultTv.text = getConverterFormat(it.result)
                    }
                }
            }
        }
    }
    private fun getConverterFormat(result: ExchangeResponse):String{
        return String.format("%.2f${result.query.to}",result.result)
    }
}