package com.dtoanng.jetpack_compose_instagram.core.presentation

sealed class Screen(val route: String) {
    data object SignIn: Screen("sign_in")
    data object SignUp: Screen("sign_up")
    data object Main: Screen("main_screen")
    data object Home: Screen("home")
}