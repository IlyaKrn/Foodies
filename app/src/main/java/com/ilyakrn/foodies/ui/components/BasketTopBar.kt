package com.ilyakrn.foodies.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilyakrn.foodies.R

//верхняя панель корзины
@Preview
@Composable
fun BasketTopBar(
    onClose: () -> Unit = {},
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .shadow(4.dp)
        .background(MaterialTheme.colorScheme.background)
        .height(56.dp)
    ){
        //кнопка назад
        Icon(modifier = Modifier
            .padding(16.dp)
            .align(Alignment.CenterVertically)
            .clickable {
                onClose()
            },
            painter = painterResource(id = R.drawable.back),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = "settings"
        )
        Spacer(modifier = Modifier.width(16.dp))
        // надпись "Корзина"
        Text(modifier = Modifier
            .align(Alignment.CenterVertically),
            text = stringResource(R.string.basket),
            style = MaterialTheme.typography.titleLarge
        )
    }
}