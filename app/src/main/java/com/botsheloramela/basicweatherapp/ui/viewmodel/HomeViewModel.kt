package com.botsheloramela.basicweatherapp.ui.viewmodel

import android.location.Location
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.botsheloramela.basicweatherapp.domain.model.CurrentWeather
import com.botsheloramela.basicweatherapp.domain.model.WeatherForecast
import com.botsheloramela.basicweatherapp.domain.usecase.GetCurrentWeatherUseCase
import com.botsheloramela.basicweatherapp.domain.usecase.GetDeviceLocationUseCase
import com.botsheloramela.basicweatherapp.domain.usecase.GetWeatherForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
    private val getDeviceLocationUseCase: GetDeviceLocationUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
): ViewModel() {
    val locationState = mutableStateOf<Location?>(null)
    val weatherForecastState = mutableStateOf<WeatherForecast?>(null)
    val currentWeatherState = mutableStateOf<CurrentWeather?>(null)

    // Fetch the device location
    fun getDeviceLocation() {
        viewModelScope.launch {
            val location = getDeviceLocationUseCase()
            locationState.value = location
        }
    }

    // Fetch the weather forecast based on the current location
    fun getWeatherForecast() {
        viewModelScope.launch {
            val location = locationState.value
            if (location != null) {
                val weatherForecast = getWeatherForecastUseCase(
                    latitude = location.latitude,
                    longitude = location.longitude
                )
                weatherForecastState.value = weatherForecast
            }
        }
    }

    fun getCurrentWeather() {
        viewModelScope.launch {
            val location = locationState.value
            if (location != null) {
                val currentWeather = getCurrentWeatherUseCase(
                    latitude = location.latitude,
                    longitude = location.longitude
                )
                currentWeatherState.value = currentWeather
            }
        }
    }
}