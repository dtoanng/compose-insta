package com.dtoanng.compose_image_threads.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dtoanng.compose_image_threads.core.presentation.ImageThreadsViewModel
import com.dtoanng.compose_image_threads.core.presentation.Screen
import com.dtoanng.compose_image_threads.presentation.signin.SignInScreen
import com.dtoanng.compose_image_threads.presentation.signup.SignUpScreen
import timber.log.Timber

@Composable
fun ImageThreadsNavigation(
    navController: NavHostController,
    viewModel: ImageThreadsViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Signup.route
    ) {
        composable(Screen.Home.route) {
            Timber.d("Screen.Home : ${Screen.Home.route}")
        }

        composable(Screen.Signup.route) {
            Timber.d("Screen.Signup : ${Screen.Signup.route}")
            SignInScreen(navController, viewModel)
        }
    }
}