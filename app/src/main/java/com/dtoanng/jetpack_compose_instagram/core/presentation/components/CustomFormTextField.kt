package com.dtoanng.jetpack_compose_instagram.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dtoanng.jetpack_compose_instagram.R
import com.dtoanng.jetpack_compose_instagram.core.presentation.ui.theme.AccentColor
import com.dtoanng.jetpack_compose_instagram.core.presentation.ui.theme.FormFieldBgDark
import com.dtoanng.jetpack_compose_instagram.core.presentation.ui.theme.FormFieldBgLight
import com.dtoanng.jetpack_compose_instagram.core.presentation.ui.theme.FormFieldBorderDark
import com.dtoanng.jetpack_compose_instagram.core.presentation.ui.theme.FormFieldBorderLight
import com.dtoanng.jetpack_compose_instagram.core.presentation.ui.theme.LightBlack
import com.dtoanng.jetpack_compose_instagram.core.presentation.ui.theme.LightGray

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
    val keyboardTransform =
        if (visualTransformation == PasswordVisualTransformation()) KeyboardType.Password else keyboardType

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .border(
                width = 1.dp,
                color = if (isDarkTheme) FormFieldBorderDark else FormFieldBorderLight,
                shape = RoundedCornerShape(15.dp)
            )
            .background(if (isDarkTheme) FormFieldBgDark else FormFieldBgLight)
            .padding(4.dp),
        placeholder = {
            Text(
                text = value,
                fontSize = 14.sp,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif
                ),
                fontWeight = FontWeight.Bold
            )
        },
        label = {
            val style = if (isTextFieldFocused.value) TextStyle( // todo: need to fix (label isn't scale)
                fontFamily = FontFamily.SansSerif,
                fontSize = 12.sp,
                color = LightBlack
            ) else TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 14.sp,
                color = LightGray
            )
            Text(
                text = hint,
                style = style,
                fontWeight = FontWeight.Bold
            )
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
            focusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
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
                            contentDescription = stringResource(R.string.show_or_hide_pass_icon),
                            tint = LightGray
                        )
                    }
                )
            }
        }
    )
}