package com.botsheloramela.basicweatherapp.ui.views

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.botsheloramela.basicweatherapp.domain.model.WeatherForecast
import com.botsheloramela.basicweatherapp.ui.components.MainWeatherCard
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
            }

            if (weatherForecast != null) {
                HomeScreenContent(weatherForecast!!)
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
fun HomeScreenContent(weatherForecast: WeatherForecast) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SCREEN_PADDING)
    ) {
        MainWeatherCard(
            currentTemp = weatherForecast.list[0].main.temp.toInt(),
            feelsLikeTemp = weatherForecast.list[0].main.feels_like.toInt(),
            location = weatherForecast.city.name
        )
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