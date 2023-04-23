package com.example.fourthsemestr1.di.module

import com.example.fourthsemestr1.data.api.repository.WeatherRepositoryImpl
import com.example.fourthsemestr1.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepoModule {

    @Singleton
    @Binds
    fun bindWeatherRepository(
        impl: WeatherRepositoryImpl,
    ): WeatherRepository
}