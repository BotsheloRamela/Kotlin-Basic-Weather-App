package com.botsheloramela.basicweatherapp.data.repository

import com.botsheloramela.basicweatherapp.data.remote.WeatherApi
import com.botsheloramela.basicweatherapp.domain.model.CurrentWeather
import com.botsheloramela.basicweatherapp.domain.model.WeatherForecast
import javax.inject.Inject

/**
 * Repository for weather data.
 */
interface WeatherRepository {
    /**
     * Get the weather forecast for a given latitude and longitude
     */
    suspend fun getWeatherForecast(latitude: Double, longitude: Double): WeatherForecast

    /**
     * Get the current weather for a given latitude and longitude
     */
    suspend fun getCurrentWeather(latitude: Double, longitude: Double): CurrentWeather
}