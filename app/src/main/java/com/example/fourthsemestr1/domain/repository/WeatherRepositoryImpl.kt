package com.example.fourthsemestr1.domain.repository

import com.example.fourthsemestr1.data.api.Api
import com.example.fourthsemestr1.data.api.mapper.WeatherMapper
import com.example.fourthsemestr1.domain.entity.Weather

class WeatherRepositoryImpl(
    private val api: Api,
    private val weatherMapper: WeatherMapper
) : WeatherRepository {

    override suspend fun getWeather(city: String): Weather {
        return weatherMapper.map(api.getWeather(city))
    }

    override suspend fun getWeather(id: Int): Weather {
        return weatherMapper.map(api.getWeather(id))
    }

    override suspend fun getWeatherList(lat: Double, lon: Double, cnt: Int): MutableList<Weather> {
        val citiesResponse = api.getWeathers(lat, lon, cnt)
        val list = ArrayList<Weather>(cnt)
        for (city in citiesResponse.list) {
            list.add(weatherMapper.map(city))
        }
        return list
    }
}

