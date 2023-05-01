package com.example.fourthsemestr1.presentation.fragments.cities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fourthsemestr1.domain.entity.Weather
import com.example.fourthsemestr1.domain.usecase.GetWeatherUseCase
import com.example.fourthsemestr1.domain.usecase.GetWeatherListUseCase
import com.example.fourthsemestr1.presentation.common.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getWeatherListUseCase: GetWeatherListUseCase
) : ViewModel() {

    private val _weatherList: MutableLiveData<Result<List<Weather>>> = MutableLiveData()
    val weatherList: LiveData<Result<List<Weather>>> = _weatherList

    private val _cityId: SingleLiveEvent<Result<Int>> = SingleLiveEvent()
    val cityId: LiveData<Result<Int>> = _cityId

    fun onGetWeatherList(lat: Double, lon: Double, cnt: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                getWeatherListUseCase(lat, lon, cnt)
            }.onSuccess {
                _weatherList.value = Result.success(it)
            }.onFailure {
                _weatherList.value = Result.failure(it)
            }
        }
    }

    fun onGetCityId(cityName: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                getWeatherUseCase(cityName).id
            }.onSuccess {
                _cityId.value = Result.success(it)
            }.onFailure {
                _cityId.value = Result.failure(it)
            }
        }
    }
}