package com.dtoanng.jetpack_compose_instagram.core.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dtoanng.jetpack_compose_instagram.core.presentation.ui.theme.AccentColor
import com.dtoanng.jetpack_compose_instagram.core.presentation.ui.theme.IconDark

@Composable
fun CustomRaisedButton(
    modifier: Modifier,
    text: String,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = AccentColor,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(25.dp),
        onClick = onClick
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = IconDark, modifier = Modifier.size(30.dp))
        } else {
            Text(
                text = text,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}