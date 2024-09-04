package com.botsheloramela.basicweatherapp.data.repository

import com.botsheloramela.basicweatherapp.data.remote.WeatherApi
import com.botsheloramela.basicweatherapp.domain.model.WeatherForecast
import javax.inject.Inject

/**
 * Repository for weather data.
 */
class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) {
    /**
     * Get the weather forecast for a given latitude and longitude
     */
    suspend fun getWeatherForecast(latitude: Double, longitude: Double): WeatherForecast {
        return try {
            weatherApi.getWeatherForecast(latitude, longitude)
        } catch (e: Exception) {
            throw e // TODO: Handle error properly
        }
    }
}