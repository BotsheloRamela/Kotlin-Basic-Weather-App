package com.botsheloramela.basicweatherapp.data.repository

import com.botsheloramela.basicweatherapp.data.remote.WeatherApi
import com.botsheloramela.basicweatherapp.domain.model.WeatherForecast
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
): WeatherRepository {
    override suspend fun getWeatherForecast(latitude: Double, longitude: Double): WeatherForecast {
        return weatherApi.getWeatherForecast(latitude, longitude)
    }
}