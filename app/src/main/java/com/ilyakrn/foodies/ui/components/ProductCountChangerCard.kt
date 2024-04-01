package com.ilyakrn.foodies.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
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

@Preview
@Composable
fun ProductCountChangerCard(
    count: Int = 0,
    onAdd: () -> Unit = {},
    onRemove: () -> Unit = {}
) {
    Box(modifier = Modifier
        .width(148.dp)
        .height(40.dp)
    ){
        Box(modifier = Modifier
            .shadow(4.dp)
            .size(40.dp)
            .align(Alignment.CenterStart)
            .background(MaterialTheme.colorScheme.background, MaterialTheme.shapes.small)
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
            .align(Alignment.Center),
            text = count.toString()
        )
        Box(modifier = Modifier
            .shadow(4.dp)
            .size(40.dp)
            .align(Alignment.CenterEnd)
            .background(MaterialTheme.colorScheme.background, MaterialTheme.shapes.small)
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