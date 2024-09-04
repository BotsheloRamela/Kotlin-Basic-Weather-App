package com.botsheloramela.basicweatherapp.domain.model

/**
 * Data class representing the weather forecast for a city
 */
data class WeatherForecast (
    val list: List<WeatherItem>,
    val city: CityData
)