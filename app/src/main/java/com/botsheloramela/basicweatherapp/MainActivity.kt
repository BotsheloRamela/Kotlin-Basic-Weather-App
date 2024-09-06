package com.botsheloramela.basicweatherapp

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.botsheloramela.basicweatherapp.ui.theme.BasicWeatherAppTheme
import com.botsheloramela.basicweatherapp.ui.viewmodel.HomeViewModel
import com.botsheloramela.basicweatherapp.ui.views.HomeView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Initialize the request permission launcher
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                setContent {
                    BasicWeatherAppTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.primary
                        ) {
                            val homeViewModel: HomeViewModel = hiltViewModel()
                            HomeView(viewModel = homeViewModel)
                        }
                    }
                }
            } else {
                showPermissionDeniedMessage()
            }
        }

        requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)

    }

    private fun showPermissionDeniedMessage() {
        Toast.makeText(this, "Location permission is required to access weather data.", Toast.LENGTH_LONG).show()
    }
}
