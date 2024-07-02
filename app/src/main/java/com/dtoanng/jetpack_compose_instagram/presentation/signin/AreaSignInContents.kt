package com.dtoanng.jetpack_compose_instagram.presentation.signin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dtoanng.jetpack_compose_instagram.core.presentation.components.CustomFormTextField
import com.dtoanng.jetpack_compose_instagram.core.presentation.components.CustomRaisedButton
import com.dtoanng.jetpack_compose_instagram.core.presentation.ui.theme.LightBlack

@Composable
fun AreaSignInContents(modifier: Modifier) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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

        CustomRaisedButton(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
            text = "Log in",
            isLoading = false,
            onClick = {}
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            style = TextStyle(
                fontSize = 15.sp,
                color = LightBlack,
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