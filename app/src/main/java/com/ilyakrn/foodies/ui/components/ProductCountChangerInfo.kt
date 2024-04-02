package com.ilyakrn.foodies.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.ilyakrn.foodies.domain.usecases.AddProductToBasketUseCase
import com.ilyakrn.foodies.domain.usecases.GetProductByIdUseCase
import com.ilyakrn.foodies.domain.usecases.RemoveProductFromBasketUseCase

//панель изменения колличества продукта в корзине (из карточки продукта)
@Preview
@Composable
fun ProductCountChangerInfo(
    count: Int = 0,
    onAdd: () -> Unit = {},
    onRemove: () -> Unit = {}
) {
    Box(modifier = Modifier
        .padding(16.dp)
        .width(200.dp)
    ){
        //уменьшить
        Box(modifier = Modifier
            .shadow(4.dp,
                MaterialTheme.shapes.small)
            .size(50.dp)
            .align(Alignment.CenterStart)
            .background(
                MaterialTheme.colorScheme.primary,
                MaterialTheme.shapes.small
            )
            .clickable {
                onRemove()
            }
        ) {
            Icon(modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = R.drawable.remove),
                contentDescription = "remove",
            )
        }
        //колличество продукта
        Text(modifier = Modifier
            .align(Alignment.Center),
            text = count.toString()
        )
        //увеличить
        Box(modifier = Modifier
            .shadow(4.dp,
                MaterialTheme.shapes.small)
            .size(50.dp)
            .align(Alignment.CenterEnd)
            .background(MaterialTheme.colorScheme.primary, MaterialTheme.shapes.small)
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