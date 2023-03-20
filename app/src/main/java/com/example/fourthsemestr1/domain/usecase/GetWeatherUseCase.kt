package com.example.fourthsemestr1.domain.usecase

import com.example.fourthsemestr1.domain.entity.Weather
import com.example.fourthsemestr1.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) {

    suspend operator fun invoke(city: String): Weather {
        return withContext(dispatcher) {
            weatherRepository.getWeather(city)
        }
    }

    suspend operator fun invoke(id: Int): Weather {
        return withContext(dispatcher) {
            weatherRepository.getWeather(id)
        }
    }
}

