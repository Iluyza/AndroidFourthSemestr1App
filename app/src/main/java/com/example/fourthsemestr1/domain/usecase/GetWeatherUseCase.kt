package com.example.fourthsemestr1.domain.usecase

import com.example.fourthsemestr1.di.module.AppModule
import com.example.fourthsemestr1.domain.entity.Weather
import com.example.fourthsemestr1.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    @AppModule.DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.Main
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
