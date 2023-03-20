package com.example.fourthsemestr1.domain.usecase

import com.example.fourthsemestr1.domain.entity.Weather
import com.example.fourthsemestr1.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetWeathersUseCase(
    private val weatherRepository: WeatherRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) {
    suspend operator fun invoke(lat: Double, lon: Double, cnt: Int): MutableList<Weather> {
        return withContext(dispatcher) {
            weatherRepository.getWeatherList(lat, lon, cnt)
        }
    }
}

