package com.dtoanng.compose_image_threads.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dtoanng.compose_image_threads.R

@Composable
fun SignUpContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(
                    rememberScrollState()
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val userNameState = remember { mutableStateOf(TextFieldValue()) }
            val emailState = remember { mutableStateOf(TextFieldValue()) }
            val passwordState = remember { mutableStateOf(TextFieldValue()) }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(R.string.image_threads_logo),
                    modifier = Modifier
                        .width(100.dp)
                        .padding(all = 10.dp)
                )
            }

            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.signup),
                style = TextStyle(
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.secondary
                )
            )

            OutlinedTextField(
                value = userNameState.value,
                onValueChange = { userNameState.value = it },
                modifier = Modifier.padding(all = 8.dp),
                label = {
                    Text(
                        text = stringResource(R.string.username),
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontFamily = FontFamily.Monospace,
                        ),
                    )
                })

            OutlinedTextField(
                value = emailState.value,
                onValueChange = { emailState.value = it },
                modifier = Modifier.padding(all = 8.dp),
                label = {
                    Text(
                        text = stringResource(R.string.email),
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontFamily = FontFamily.Monospace,
                        ),
                    )
                })

            OutlinedTextField(
                value = passwordState.value,
                onValueChange = { passwordState.value = it },
                modifier = Modifier.padding(all = 8.dp),
                label = {
                    Text(
                        text = stringResource(R.string.password),
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontFamily = FontFamily.Monospace,
                        ),
                    )
                },
                visualTransformation = PasswordVisualTransformation()
            )

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(all = 8.dp)
            ) {
                Text(
                    text = stringResource(R.string.signup),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontFamily = FontFamily.Monospace,
                    ),
                )
            }

            Text(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .clickable { /*TODO*/ },
                text = stringResource(R.string.already_account),
                style = TextStyle(
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.secondary
                ),
            )
        }
    }
}

@Composable
@Preview
fun SignUpContentPreview() {
    SignUpContent()
}