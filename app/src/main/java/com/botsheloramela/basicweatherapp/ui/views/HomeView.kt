package com.botsheloramela.basicweatherapp.ui.views

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.botsheloramela.basicweatherapp.R
import com.botsheloramela.basicweatherapp.domain.model.CurrentWeather
import com.botsheloramela.basicweatherapp.domain.model.WeatherForecast
import com.botsheloramela.basicweatherapp.ui.components.MainWeatherCard
import com.botsheloramela.basicweatherapp.ui.components.WeatherItemCard
import com.botsheloramela.basicweatherapp.ui.theme.BasicWeatherAppTheme
import com.botsheloramela.basicweatherapp.ui.viewmodel.HomeViewModel
import com.botsheloramela.basicweatherapp.utils.Constants.SCREEN_PADDING
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeView(
    viewModel: HomeViewModel
) {
    val location by viewModel.locationState
    val weatherForecast by viewModel.weatherForecastState
    val currentWeather by viewModel.currentWeatherState
    val permissionState = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)

    // Fetch weather when screen is displayed
    LaunchedEffect(Unit) {
        if (permissionState.status.isGranted) {
            viewModel.getDeviceLocation()
        } else {
            permissionState.launchPermissionRequest()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
    ) {
        if (location != null) {
            val errorMessage by remember { mutableStateOf<String?>(null) }
            LaunchedEffect(location) {
                viewModel.getWeatherForecast()
                viewModel.getCurrentWeather()
            }

            if (currentWeather != null) {
                HomeScreenContent(currentWeather!!)
            } else if (errorMessage != null) {
                Text(text = "Error: $errorMessage", color = MaterialTheme.colorScheme.error)
            } else {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.secondary)
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "Fetching weather data...")
            }
        } else if (location == null) {
            Text(text = "Error: Location not found", color = MaterialTheme.colorScheme.error)
        } else {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.secondary)
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = "Fetching location...")
        }
    }



}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreenContent(currentWeather: CurrentWeather) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SCREEN_PADDING)
    ) {
        MainWeatherCard(
            currentTemp = currentWeather.main.temp.toInt(),
            feelsLikeTemp = currentWeather.main.feels_like.toInt(),
            weatherType = currentWeather.weather[0].main,
            location = currentWeather.name
        )
        Spacer(modifier = Modifier.padding(12.dp))
        val columns = 3
        val spacing = 12.dp

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            horizontalArrangement = Arrangement.spacedBy(spacing)
        ) {

            items(3) { index ->
                WeatherItemCard(
                    icon = when(index) {
                        0 -> R.drawable.wind
                        1 -> R.drawable.pressure
                        else -> R.drawable.humidity
                    },
                    title = when(index) {
                        0 -> "Wind"
                        1 -> "Pressure"
                        else -> "Humidity"
                    },
                    value = "${currentWeather.wind.speed}".let {
                        when(index) {
                            0 -> "$it m/s"
                            1 -> "${currentWeather.main.pressure} MB"
                            else -> "${currentWeather.main.humidity}%"
                        }
                    }
                )
                Spacer(modifier = Modifier.width(spacing))
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    BasicWeatherAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary
        ) {
//            HomeView(
//                weatherForecast =
//            )
        }
    }
}