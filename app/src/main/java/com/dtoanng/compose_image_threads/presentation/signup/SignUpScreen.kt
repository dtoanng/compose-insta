package com.dtoanng.compose_image_threads.presentation.signup

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dtoanng.compose_image_threads.core.presentation.ImageThreadsViewModel

@Composable
fun SignUpScreen(
    navController: NavController,
    imageThreadsViewModel: ImageThreadsViewModel = hiltViewModel()
) {
    SignUpContent()
}