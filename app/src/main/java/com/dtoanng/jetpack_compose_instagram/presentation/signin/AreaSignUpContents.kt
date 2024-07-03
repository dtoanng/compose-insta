package com.dtoanng.jetpack_compose_instagram.presentation.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dtoanng.jetpack_compose_instagram.R
import com.dtoanng.jetpack_compose_instagram.core.presentation.components.CustomOutlinedButton

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