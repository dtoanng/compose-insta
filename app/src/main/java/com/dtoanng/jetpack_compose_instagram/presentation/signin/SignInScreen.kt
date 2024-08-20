package com.dtoanng.jetpack_compose_instagram.presentation.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dtoanng.jetpack_compose_instagram.R
import com.dtoanng.jetpack_compose_instagram.core.presentation.JetInstagramViewModel
import com.dtoanng.jetpack_compose_instagram.core.presentation.components.CustomFormTextField
import com.dtoanng.jetpack_compose_instagram.core.presentation.components.CustomOutlinedButton
import com.dtoanng.jetpack_compose_instagram.core.presentation.components.CustomRaisedButton
import com.dtoanng.jetpack_compose_instagram.core.presentation.ui.theme.LightBlack
import com.dtoanng.jetpack_compose_instagram.core.presentation.ui.theme.LightGray
import com.dtoanng.jetpack_compose_instagram.core.utils.Action
import com.dtoanng.jetpack_compose_instagram.core.utils.AuthEvents
import com.dtoanng.jetpack_compose_instagram.core.utils.ResultEvents
import timber.log.Timber

@Composable
fun SignInScreen(
    onClick: (Action) -> Unit,
    jetInstagramViewModel: JetInstagramViewModel = hiltViewModel()
) {
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        jetInstagramViewModel?.eventFlow?.collect { event ->
            Timber.d("event>>>: $event")
            when (event) {
                is ResultEvents.OnError -> {
                    snackBarHostState.showSnackbar(event.error, duration = SnackbarDuration.Short)
                }

                is ResultEvents.OnSuccess -> {
                    snackBarHostState.showSnackbar(event.message, duration = SnackbarDuration.Short)
                }

                is ResultEvents.OnLoading -> {
                    // todo: update loading on Sign-up button
                }

                else -> {
                    // todo: nothing
                }
            }
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(snackBarHostState) },
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
                        modifier = Modifier.weight(1f),
                        viewModel = jetInstagramViewModel
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

@Composable
fun AreaLogoContents(
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "English",
                textAlign = TextAlign.Center
            )
        }
        Image(
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .weight(5f),
            painter = painterResource(id = R.drawable.app_logo_outline),
            contentDescription = stringResource(R.string.ig_app_logo),
            alignment = Alignment.Center
        )
    }
}

@Composable
@Preview
fun AreaLogoContentsPreview() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AreaLogoContents(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )
    }
}

@Composable
fun AreaSignInContents(
    onClick: (Action) -> Unit,
    modifier: Modifier,
    viewModel: JetInstagramViewModel? = null
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isDarkTheme = isSystemInDarkTheme()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        CustomFormTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
            hint = "Phone number, username, or email",
            value = email,
            onValueChange = { email = it }
        )

        Spacer(modifier = Modifier.height(12.dp))

        CustomFormTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
            hint = "Password",
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { password = it }
        )

        Spacer(modifier = Modifier.height(12.dp))

        CustomRaisedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = "Log in",
            isLoading = false
        ) {
            Timber.d("Log in >>> clicked")
            Timber.d("Log in >>> ${viewModel == null}")
            viewModel?.onUserEvents(
                AuthEvents.OnSignIn(
                    email = email,
                    password = password
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            style = TextStyle(
                fontSize = 15.sp,
                color = if (isDarkTheme) LightGray else LightBlack,
                fontWeight = FontWeight.Bold
            ),
            text = "Forgot password?",
            textAlign = TextAlign.End,
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            ) {
                /*todo*/
            }
        )
    }
}

@Composable
fun AreaSignUpContents(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier.padding(vertical = 12.dp)
    ) {
        CustomOutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = "Create a new account",
            isLoading = false,
            onClick = onClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp),
                painter = painterResource(id = R.drawable.ic_meta),
                contentDescription = "Meta Corp Logo",
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Meta",
                fontSize = 16.sp,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif
                ),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}