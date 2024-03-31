package com.ilyakrn.foodies.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilyakrn.foodies.R
import com.ilyakrn.foodies.ui.theme.FoodiesTheme

@Preview
@Composable
fun SplashScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary)
    ){

        val isShow = remember {
            mutableStateOf(false)
        }
        AnimatedVisibility(modifier = Modifier
            .align(Alignment.Center),
            visible = isShow.value,
            enter = slideInVertically(initialOffsetY = {500}) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = {-500}) + fadeOut()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo_splash),
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = "logo"
            )
        }
        LaunchedEffect(isShow.value) {
            isShow.value = true
        }


    }
}