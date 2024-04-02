package com.ilyakrn.foodies.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilyakrn.foodies.R

//нижняя кнопка
@Composable
fun BottomButton(onClick: () -> Unit = {}, text: String = "", iconId: Int? = null) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(72.dp)
        .background(MaterialTheme.colorScheme.background)
        .clickable {

        }
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(16.dp, 12.dp)
            .align(Alignment.Center)
            .background(MaterialTheme.colorScheme.primary, MaterialTheme.shapes.small)
            .clickable {
                onClick()
            }
        ) {
            Row(modifier = Modifier.align(Alignment.Center)){
                //иконка (если есть)
                iconId?.let {
                    Icon(
                        painter = painterResource(id = iconId),
                        contentDescription = "remove",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                //текст
                Text(
                    text = text,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}