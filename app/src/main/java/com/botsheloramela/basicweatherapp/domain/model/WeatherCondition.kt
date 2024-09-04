package com.botsheloramela.basicweatherapp.domain.model

/**
 * Data class representing the weather condition, which includes the main weather condition and a description
 */
data class WeatherCondition (
    val main: String,
    val description: String
)