package com.dtoanng.jetpack_compose_instagram.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.dtoanng.jetpack_compose_instagram.core.navigation.JetInstagramNavigation
import com.dtoanng.jetpack_compose_instagram.core.presentation.ui.theme.ComposeImageThreadsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeImageThreadsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier) {
    val jetInstagramViewModel = hiltViewModel<JetInstagramViewModel>()
    val navController = rememberNavController()

    JetInstagramNavigation(navController = navController, viewModel = jetInstagramViewModel)
}