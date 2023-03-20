package com.example.fourthsemestr1.di

import com.example.fourthsemestr1.BuildConfig
import com.example.fourthsemestr1.data.api.Api
import com.example.fourthsemestr1.data.api.mapper.WeatherIconUrlMapper
import com.example.fourthsemestr1.data.api.mapper.WeatherMapper
import com.example.fourthsemestr1.data.api.mapper.WindDegMapper
import com.example.fourthsemestr1.di.interceptor.ApiKeyInterceptor
import com.example.fourthsemestr1.di.interceptor.LangInterceptor
import com.example.fourthsemestr1.di.interceptor.UnitsInterceptor
import com.example.fourthsemestr1.domain.repository.WeatherRepository
import com.example.fourthsemestr1.domain.repository.WeatherRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

object DiContainer {
    private val okhttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .addInterceptor(UnitsInterceptor())
            .addInterceptor(LangInterceptor())
            .also {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(
                        HttpLoggingInterceptor()
                            .setLevel(
                                HttpLoggingInterceptor.Level.BODY
                            )
                    )
                }
            }
            .build()
    }

    val api: Api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    private val windDegMapper: WindDegMapper by lazy {
        WindDegMapper()
    }

    private val weatherIconUrlMapper by lazy {
        WeatherIconUrlMapper()
    }

    val weatherMapper: WeatherMapper by lazy {
        WeatherMapper(
            windDegMapper,
            weatherIconUrlMapper
        )
    }

    val weatherRepository: WeatherRepository by lazy {
        WeatherRepositoryImpl(
            api,
            weatherMapper
        )
    }
}

