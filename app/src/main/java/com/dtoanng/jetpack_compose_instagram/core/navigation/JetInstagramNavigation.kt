package com.dtoanng.jetpack_compose_instagram.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dtoanng.jetpack_compose_instagram.core.presentation.JetInstagramViewModel
import com.dtoanng.jetpack_compose_instagram.core.presentation.Screen
import com.dtoanng.jetpack_compose_instagram.presentation.signin.SignInScreen
import com.dtoanng.jetpack_compose_instagram.presentation.signup.SignUpScreen
import timber.log.Timber

@Composable
fun JetInstagramNavigation(
    navController: NavHostController,
    viewModel: JetInstagramViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SignIn.route
    ) {

        composable(Screen.SignIn.route) {
            Timber.d("Screen.SignIn : ${Screen.SignIn.route}")
            SignInScreen(navController, viewModel)
        }

        composable(Screen.SignUp.route) {
            Timber.d("Screen.Signup : ${Screen.SignUp.route}")
            SignUpScreen(navController, viewModel)
        }

        composable(Screen.Home.route) {
            Timber.d("Screen.Home : ${Screen.Home.route}")
        }

    }
}