package com.dtoanng.jetpack_compose_instagram.presentation.signin

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
import androidx.navigation.compose.rememberNavController
import com.dtoanng.jetpack_compose_instagram.core.presentation.JetInstagramViewModel
import com.dtoanng.jetpack_compose_instagram.core.utils.Action

@Composable
fun SignInScreen(
    onClick: (Action) -> Unit,
    jetInstagramViewModel: JetInstagramViewModel? = null
) {

    Scaffold( // todo: impl bottom sheet to pick language
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

                    AreaSignInContents(
                        onClick = { onClick(Action.SIGN_IN) },
                        modifier = Modifier.weight(1f)
                    )

                    AreaSignUpContents(
                        onClick = { onClick(Action.GOTO_SIGN_UP) },
                        modifier = Modifier.weight(0.8f),
                    )
                }
            }
        }
    )
}

@Composable
@Preview
fun SignInScreenPreview() {
    SignInScreen(
        onClick = {}
    )
}