package com.dtoanng.compose_image_threads.presentation.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dtoanng.compose_image_threads.R

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