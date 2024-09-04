package com.botsheloramela.basicweatherapp.data.remote

import com.botsheloramela.basicweatherapp.domain.model.WeatherForecast
import com.botsheloramela.basicweatherapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface for defining the OpenWeatherMap API endpoints.
 */
interface WeatherApi {
    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = API_KEY,
    ): WeatherForecast
}