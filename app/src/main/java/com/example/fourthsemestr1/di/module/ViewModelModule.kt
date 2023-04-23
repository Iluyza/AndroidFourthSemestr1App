package com.example.fourthsemestr1.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fourthsemestr1.di.annotation.ViewModelKey
import com.example.fourthsemestr1.presentation.common.utils.AppViewModelFactory
import com.example.fourthsemestr1.presentation.fragments.cities.CityListViewModel
import com.example.fourthsemestr1.presentation.fragments.weather.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(
        factory: AppViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CityListViewModel::class)
    fun bindCityListViewModel(
        viewModel: CityListViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    fun bindWeatherViewModel(
        viewModel: WeatherViewModel
    ): ViewModel
}