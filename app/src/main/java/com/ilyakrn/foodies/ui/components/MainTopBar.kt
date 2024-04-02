package com.ilyakrn.foodies.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilyakrn.foodies.R

//верхняя панель экрана категорий
@Preview
@Composable
fun MainTopBar(
    onSettings: () -> Unit = {},
    onSearch: () -> Unit = {}
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background)
        .height(60.dp)
    ){
        //фильтры
        Icon(modifier = Modifier
            .padding(18.dp)
            .align(Alignment.CenterStart)
            .clickable {
                onSettings()
            },
            painter = painterResource(id = R.drawable.settings),
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = "settings"
        )
        //лого
        Icon(modifier = Modifier
            .align(Alignment.Center),
            painter = painterResource(id = R.drawable.logo_top_bar),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = "settings"
        )
        //поиск
        Icon(modifier = Modifier
            .padding(18.dp)
            .align(Alignment.CenterEnd)
            .clickable {
                onSearch()
            },
            painter = painterResource(id = R.drawable.search),
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = "settings"
        )
    }
}