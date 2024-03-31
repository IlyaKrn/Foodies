package com.ilyakrn.foodies.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ilyakrn.foodies.R
import com.ilyakrn.foodies.ui.components.GifImage

@Preview
@Composable
fun ProductInfoScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary)
    ){

        Text(text = "basket")


    }
}