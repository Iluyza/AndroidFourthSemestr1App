package com.example.fourthsemestr1.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.fourthsemestr1.data.WeatherRepository
import com.example.fourthsemestr1.databinding.ActivityMainBinding
import com.example.fourthsemestr1.extention.findController
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var controller: NavController? = null
    private val repository by lazy {
        WeatherRepository()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        controller = binding?.navHostFragmentMain?.id?.let { findController(it) }

        lifecycleScope.launch {
            kotlin.runCatching {
                val response = repository.getWeather("Kazan")
                Log.e("Response","$response")
            } .onFailure {
                Log.e("arg", it.message.toString())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        controller = null
    }
}

