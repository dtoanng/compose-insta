package com.dtoanng.jetpack_compose_instagram.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dtoanng.jetpack_compose_instagram.R
import com.dtoanng.jetpack_compose_instagram.core.data.firebase.UserDto
import com.dtoanng.jetpack_compose_instagram.core.presentation.JetInstagramViewModel
import com.dtoanng.jetpack_compose_instagram.core.presentation.components.CustomFormTextField
import com.dtoanng.jetpack_compose_instagram.core.presentation.components.CustomLinkText
import com.dtoanng.jetpack_compose_instagram.core.presentation.components.CustomLinkTextData
import com.dtoanng.jetpack_compose_instagram.core.presentation.components.CustomOutlinedButton
import com.dtoanng.jetpack_compose_instagram.core.presentation.components.CustomRaisedButton
import com.dtoanng.jetpack_compose_instagram.core.presentation.ui.theme.AccentColor
import com.dtoanng.jetpack_compose_instagram.core.presentation.ui.theme.LightBlack
import com.dtoanng.jetpack_compose_instagram.core.presentation.ui.theme.LightGray
import com.dtoanng.jetpack_compose_instagram.core.presentation.ui.theme.LineGrayColor
import com.dtoanng.jetpack_compose_instagram.core.utils.Action
import com.dtoanng.jetpack_compose_instagram.core.utils.AuthEvents
import com.dtoanng.jetpack_compose_instagram.core.utils.ResultEvents
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@Composable
fun SignUpScreen(
    onClick: (Action) -> Unit,
    jetInstagramViewModel: JetInstagramViewModel? = null
) {
    val isDarkTheme = isSystemInDarkTheme()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = 1) {
        jetInstagramViewModel?.eventFlow?.collect { event ->
            when (event) {
                is ResultEvents.OnError -> {
                    snackBarHostState.showSnackbar(event.error, duration = SnackbarDuration.Short)
                }

                is ResultEvents.OnSuccess -> {
                    snackBarHostState.showSnackbar(event.message, duration = SnackbarDuration.Short)
                    onClick(Action.BACK)
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

    Scaffold( // todo: impl bottom sheet to pick user profile image
        snackbarHost = { SnackbarHost(snackBarHostState) },
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(all = 2.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    FormFieldArea(isDarkTheme = isDarkTheme, onClick = onClick, viewModel = jetInstagramViewModel)
                }

                BottomLogInArea(
                    onClick = onClick,
                    isDarkTheme = isDarkTheme,
                )

                IconButton(
                    onClick = { onClick(Action.BACK) },
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .align(Alignment.TopStart)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = "Back button",
                        Modifier.size(20.dp)
                    )
                }
            }
        }
    )
}

@Composable
@Preview
fun SignUpScreenPreview() {
    SignUpScreen(onClick = {})
}

@Composable
fun FormFieldArea(
    isDarkTheme: Boolean,
    onClick: (Action) -> Unit,
    viewModel: JetInstagramViewModel?
) {
    var email by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Spacer(modifier = Modifier.padding(vertical = 20.dp))

    Image(
        modifier = Modifier
            .width(50.dp)
            .height(50.dp)
            .padding(top = 15.dp),
        painter = painterResource(id = R.drawable.app_logo_outline),
        contentDescription = stringResource(R.string.ig_app_logo),
        alignment = Alignment.Center
    )

    Spacer(modifier = Modifier.padding(vertical = 14.dp))

    Text(
        modifier = Modifier.padding(horizontal = 10.dp),
        style = TextStyle(
            color = LightGray,
            fontSize = 18.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold
        ),
        text = "Sign up to see photos and videos from your friends.",
        textAlign = TextAlign.Center,
        maxLines = 2
    )

    Spacer(modifier = Modifier.padding(vertical = 10.dp))

    CustomOutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        text = "Log in with Facebook",
        resId = R.drawable.ic_facebook,
        isLoading = false,
        onClick = {
        }
    )

    Spacer(modifier = Modifier.padding(vertical = 10.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        HorizontalDivider(color = LineGrayColor, modifier = Modifier.weight(1f))
        Text(
            text = "OR",
            modifier = Modifier
                .weight(0.3f)
                .padding(2.dp),
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = LightGray,
                fontWeight = FontWeight.Bold
            )
        )
        HorizontalDivider(color = LineGrayColor, modifier = Modifier.weight(1f))
    }

    Spacer(modifier = Modifier.padding(vertical = 10.dp))

    CustomFormTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        hint = "Email",
        value = email,
        onValueChange = { email = it },
        keyboardType = KeyboardType.Email
    )

    Spacer(modifier = Modifier.height(6.dp))

    CustomFormTextField(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp),
        hint = "Full Name",
        value = fullName,
        onValueChange = { fullName = it }
    )

    Spacer(modifier = Modifier.height(6.dp))

    CustomFormTextField(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp),
        hint = "Username",
        value = userName,
        onValueChange = { userName = it }
    )

    Spacer(modifier = Modifier.height(6.dp))

    CustomFormTextField(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp),
        hint = "Password",
        value = password,
        visualTransformation = PasswordVisualTransformation(),
        onValueChange = { password = it }
    )

    Spacer(modifier = Modifier.height(20.dp))

    // textview
    CustomLinkText(
        linkTextData = listOf(
            CustomLinkTextData(
                text = "People who use our service may have uploaded your contact information to Instagram. ",
            ),
            CustomLinkTextData(
                text = "Learn More",
                tag = "learn_more_des",
                annotation = "https://help.instagram.com/195069860617299?helpref=about_content",
                onClick = {}
            )
        ),
        modifier = Modifier.padding(horizontal = 32.dp),
    )

    Spacer(modifier = Modifier.height(12.dp))

    CustomLinkText(
        linkTextData = listOf(
            CustomLinkTextData(
                text = "By signing up, you agree to our ",
            ),
            CustomLinkTextData(
                text = "Terms",
                annotation = "https://help.instagram.com/195069860617299?helpref=about_content",
                tag = "terms_des",
                onClick = {}
            ),
            CustomLinkTextData(
                text = ", ",
            ),
            CustomLinkTextData(
                text = "Privacy Policy ",
                annotation = "https://help.instagram.com/195069860617299?helpref=about_content",
                tag = "privacy_policy_des",
                onClick = {}
            ),
            CustomLinkTextData(
                text = "and ",
            ),
            CustomLinkTextData(
                text = "Cookies Policy",
                annotation = "https://help.instagram.com/195069860617299?helpref=about_content",
                tag = "cookies_policy_des",
                onClick = {}
            ),
            CustomLinkTextData(
                text = ". ",
            ),
        ),
        modifier = Modifier.padding(horizontal = 32.dp),
    )

    Spacer(modifier = Modifier.height(6.dp))

    CustomRaisedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        text = "Sign up",
        isLoading = false
    ) {
        val user = UserDto(
            email = email.trim(),
            username = userName.trim(),
            fullName = fullName.trim(),
            password = password.trim()
        )

        viewModel?.onUserEvents(
            AuthEvents.OnSignUp(
                imageUrl = null,
                userDto = user
            )
        )
    }

    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun BoxScope.BottomLogInArea(
    onClick: (Action) -> Unit,
    isDarkTheme: Boolean
) {
    Row(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Have an account?",
            style = TextStyle(
                color = if (isDarkTheme) LightGray else LightBlack,
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif
            )
        )

        Spacer(modifier = Modifier.padding(horizontal = 2.dp))

        Text(
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            ) {
                onClick(Action.BACK)
            },
            text = "Log in",
            style = TextStyle(
                color = AccentColor,
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun BottomLogInPreview() {
    Box(modifier = Modifier.fillMaxWidth()) {
        BottomLogInArea(onClick = {}, isDarkTheme = false)
    }
}