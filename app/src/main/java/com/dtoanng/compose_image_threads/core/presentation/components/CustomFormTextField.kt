package com.dtoanng.compose_image_threads.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.dtoanng.compose_image_threads.R
import com.dtoanng.compose_image_threads.core.presentation.ui.theme.AccentColor
import com.dtoanng.compose_image_threads.core.presentation.ui.theme.FormFieldBgDark
import com.dtoanng.compose_image_threads.core.presentation.ui.theme.FormFieldBgLight
import com.dtoanng.compose_image_threads.core.presentation.ui.theme.FormFieldBorderDark
import com.dtoanng.compose_image_threads.core.presentation.ui.theme.FormFieldBorderLight
import com.dtoanng.compose_image_threads.core.presentation.ui.theme.LightGray

@Composable
fun CustomFormTextField(
    modifier: Modifier,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text
) {

    val isDarkTheme = isSystemInDarkTheme()
    val keyBoardController = LocalSoftwareKeyboardController.current
    val isTextFieldFocused = remember { MutableInteractionSource() }.collectIsFocusedAsState()
    var isPasswordVisible by remember { mutableStateOf(false) }
    val transformation = if (visualTransformation == PasswordVisualTransformation()) {
        if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
    } else visualTransformation
    val keyboardTransform = if (visualTransformation == PasswordVisualTransformation()) KeyboardType.Password else keyboardType

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .border(
                width = 1.dp,
                color = if (isDarkTheme) FormFieldBorderDark else FormFieldBorderLight,
                shape = RoundedCornerShape(15.dp)
            )
            .background(if (isDarkTheme) FormFieldBgDark else FormFieldBgLight),
        placeholder = {
            Text(text = value.ifEmpty { hint })
        },
        label = {
            val style = if (isTextFieldFocused.value) MaterialTheme.typography.titleSmall else MaterialTheme.typography.bodyLarge
            Text(text = hint, style = style)
        },
        singleLine = true,
        visualTransformation = transformation,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardTransform,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyBoardController?.hide()
            }
        ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedTextColor = LightGray,
            cursorColor = AccentColor,
        ),
        trailingIcon = {
            if (visualTransformation == PasswordVisualTransformation()) {
                IconButton(
                    onClick = { isPasswordVisible = !isPasswordVisible },
                    content = {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = stringResource(R.string.show_or_hide_pass_icon)
                        )
                    }
                )
            }
        }
    )
}