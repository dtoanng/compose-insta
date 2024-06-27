package com.dtoanng.compose_image_threads.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dtoanng.compose_image_threads.R
import com.dtoanng.compose_image_threads.core.presentation.ImageThreadsViewModel
import com.dtoanng.compose_image_threads.core.presentation.components.CustomFormTextField
import com.dtoanng.compose_image_threads.core.presentation.components.CustomRaisedButton
import com.dtoanng.compose_image_threads.core.presentation.ui.theme.AccentColor
import com.dtoanng.compose_image_threads.core.presentation.ui.theme.LightBlack
import com.dtoanng.compose_image_threads.core.presentation.ui.theme.LightGray
import com.dtoanng.compose_image_threads.core.presentation.ui.theme.LineBlueColor
import com.dtoanng.compose_image_threads.core.presentation.ui.theme.LineGrayColor

@Composable
fun SignUpScreen(
    navController: NavController,
    imageThreadsViewModel: ImageThreadsViewModel? = null
) {
    val isDarkTheme = isSystemInDarkTheme()
    Scaffold( // todo: impl bottom sheet to pick user profile image
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(all = 2.dp),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    FormFieldArea(isDarkTheme = isDarkTheme)
                }

                BottomLogInArea(
                    isDarkTheme = isDarkTheme,
                )
            }
        })
}

@Composable
@Preview
fun SignUpScreenPreview() {
    val navController = rememberNavController()
    SignUpScreen(navController = navController)
}

@Composable
fun FormFieldArea(isDarkTheme: Boolean) {
    var emailOrPhone by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (isDarkTheme) LineBlueColor else LineGrayColor
            )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Image(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp),
                painter = painterResource(id = R.drawable.app_logo_outline),
                contentDescription = stringResource(R.string.ig_app_logo),
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.padding(vertical = 14.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                style = TextStyle(
                    color = LightGray,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                ),
                text = "Sign up to see photos and videos from your friends.",
                textAlign = TextAlign.Center,
                maxLines = 2
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            CustomRaisedButton(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
                text = "Log in",
                isLoading = false,
                onClick = {}
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
                        .weight(0.5f)
                        .padding(5.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = LightGray,
                        fontWeight = FontWeight.Bold
                    )
                )
                HorizontalDivider(color = LineGrayColor, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            CustomFormTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
                hint = "Phone number, username, or email",
                value = emailOrPhone,
                onValueChange = { emailOrPhone = it }
            )

            Spacer(modifier = Modifier.height(12.dp))

            CustomFormTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
                hint = "Full name",
                value = fullName,
                onValueChange = { fullName = it }
            )

            Spacer(modifier = Modifier.height(12.dp))

            CustomFormTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
                hint = "Username",
                value = userName,
                onValueChange = { userName = it }
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

            // textview


            Spacer(modifier = Modifier.height(12.dp))

            CustomRaisedButton(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
                text = "Sign up",
                isLoading = false,
                onClick = {}
            )

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun BoxScope.BottomLogInArea(isDarkTheme: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .border(
                width = 1.dp,
                color = if (isDarkTheme) LineBlueColor else LineGrayColor,
            )
            .padding(30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Have an account?",
            style = TextStyle(
                color = LightBlack,
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif
            )
        )

        Spacer(modifier = Modifier.padding(horizontal = 2.dp))

        Text(
            modifier = Modifier.clickable { /*todo*/ },
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
        BottomLogInArea(isDarkTheme = false)
    }
}