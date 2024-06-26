package com.dtoanng.compose_image_threads.core.presentation

sealed class Screen(val route: String) {
    data object SignIn: Screen("sign_in")
    data object SignUp: Screen("sign_up")
    data object Home: Screen("home")
}