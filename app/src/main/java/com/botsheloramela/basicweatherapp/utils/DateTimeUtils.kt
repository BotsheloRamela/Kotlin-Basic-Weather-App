package com.botsheloramela.basicweatherapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Utility class for date and time operations.
 */
object DateTimeUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    private val dateFormat = DateTimeFormatter.ofPattern("d MMMM, EEEE")

    @RequiresApi(Build.VERSION_CODES.O)
    /**
     * Get the current date in the format "d MMMM, EEEE".
     */
    fun getCurrentDate(): String {
        val currentDate = LocalDate.now()
        return currentDate.format(dateFormat)
    }
}