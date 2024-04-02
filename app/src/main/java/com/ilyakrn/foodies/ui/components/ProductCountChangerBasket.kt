package com.ilyakrn.foodies.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilyakrn.foodies.R

@Composable
fun ProductCountChangerBasket(
    count: Int,
    onAdd: () -> Unit = {},
    onRemove: () -> Unit = {}
) {
    Row(modifier = Modifier
        .height(40.dp)
    ){
        Box(modifier = Modifier
            .size(40.dp)
            .align(Alignment.CenterVertically)
            .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.small)
            .clickable {
                onRemove()
            }
        ) {
            Icon(modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = R.drawable.remove),
                contentDescription = "remove",
            )
        }
        Text(modifier = Modifier
            .padding(16.dp, 0.dp)
            .align(Alignment.CenterVertically),
            text = count.toString()
        )
        Box(modifier = Modifier
            .size(40.dp)
            .align(Alignment.CenterVertically)
            .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.small)
            .clickable {
                onAdd()
            }
        ) {
            Icon(modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = R.drawable.add),
                contentDescription = "remove"
            )
        }
    }

}