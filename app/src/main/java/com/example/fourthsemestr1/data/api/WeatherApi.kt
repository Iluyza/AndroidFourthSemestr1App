package com.example.fourthsemestr1.data.api

import com.example.fourthsemestr1.data.api.response.citiesResponse.CitiesResponse
import com.example.fourthsemestr1.data.api.response.weatherResponse.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(@Query("q") city: String): WeatherResponse

    @GET("find")
    suspend fun getWeatherCities(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("cnt") cnt: Int
    ) : CitiesResponse
}

