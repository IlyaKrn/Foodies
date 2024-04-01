package com.ilyakrn.foodies.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProductInfoParam(param: String, value: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .background(MaterialTheme.colorScheme.background)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp)
            .align(Alignment.Center)
        ) {
            Text(modifier =
            Modifier.align(Alignment.CenterStart),
                text = param,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.tertiary,
                textAlign = TextAlign.Center
            )
            Text(modifier = Modifier.align(Alignment.CenterEnd),
                text = value,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}