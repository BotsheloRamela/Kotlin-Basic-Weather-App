package com.botsheloramela.basicweatherapp.domain.model

data class CurrentWeather (
    val weather: List<WeatherCondition>,
    val main: MainData,
    val wind: Wind,
    val name: String
)