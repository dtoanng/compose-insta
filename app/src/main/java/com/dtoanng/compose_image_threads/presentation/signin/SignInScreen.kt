package com.dtoanng.compose_image_threads.presentation.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.dtoanng.compose_image_threads.core.presentation.ImageThreadsViewModel

@Composable
fun SignInScreen(
    navHostController: NavHostController? = null,
    imageThreadsViewModel: ImageThreadsViewModel? = null
) {

    Scaffold(
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    AreaLogoContents(modifier = Modifier.weight(0.8f))
                    AreaSignInContents(modifier = Modifier.weight(1f))
                    AreaSignUpContents(modifier = Modifier.weight(0.8f))
                }
            }
        }
    )
}

@Composable
@Preview
fun SignInScreenPreview() {
    SignInScreen()
}