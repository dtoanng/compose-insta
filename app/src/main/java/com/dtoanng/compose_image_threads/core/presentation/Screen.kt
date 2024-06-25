package com.dtoanng.compose_image_threads.core.presentation

sealed class Screen(val route: String) {
    data object Signup: Screen("signup")
    data object Home: Screen("home")
}