package com.botsheloramela.basicweatherapp.utils

import com.botsheloramela.basicweatherapp.R

/**
 * Object that maps the weather id to the corresponding icon
 */
object  WeatherIconMapper {
    /**
     * Function that maps the weather id to the corresponding icon
     * @param id the weather id
     * @return the corresponding icon
     */
    fun getWeatherIcon(id: Int): Int {
        return when (id) {
            // Thunderstorm
            in 200..202, in 230..232 -> R.drawable.thunderstorm_light
            in 210..221 -> R.drawable.thunder_light
            // Drizzle
            in 300..321 -> R.drawable.drop_light
            // Rain
            in 500..504 -> R.drawable.rain_light
            511 -> R.drawable.snow_light
            in 520..531 -> R.drawable.heavy_rain_light
            // Snow
            in 600..602 -> R.drawable.snow_light
            in 611..622 -> R.drawable.heavy_snowfall_light
            // Atmosphere
            701 -> R.drawable.cloudy_night_light
            711 -> R.drawable.cloudy_night_stars_light
            721 -> R.drawable.eclipse_light
            731, in 751..761, in 762..771 -> R.drawable.heavy_wind_light
            781 -> R.drawable.thunderstorm_light
            // Clear
            800 -> R.drawable.sun_light
            // Clouds
            801 -> R.drawable.partial_cloudy_light
            in 802..804 -> R.drawable.mostly_cloudy_light
            else -> getClosestIcon(id)
        }
    }

    /**
     * Function that returns the closest icon to the weather id
     * @param id the weather id
     * @return the closest icon
     */
    private fun getClosestIcon(id: Int): Int {
        return when (id) {
            in 200..232 -> R.drawable.thunder_light
            in 300..321 -> R.drawable.drop_light
            in 500..531 -> R.drawable.rain_light
            in 600..622 -> R.drawable.snow_light
            in 701..781 -> R.drawable.cloudy_night_light
            800 -> R.drawable.sun_light
            801 -> R.drawable.partial_cloudy_light
            in 802..804 -> R.drawable.mostly_cloudy_light
            else -> R.drawable.sun_light // default icon
        }
    }
}