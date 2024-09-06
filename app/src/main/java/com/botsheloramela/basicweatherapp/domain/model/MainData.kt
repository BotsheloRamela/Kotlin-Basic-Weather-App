package com.botsheloramela.basicweatherapp.domain.model

/**
 * Data class representing the main data of the weather, which includes the temperature, pressure, and humidity
 */
data class MainData(
    val temp: Double,
    val pressure: Int,
    val humidity: Int,
    val feels_like: Double
)