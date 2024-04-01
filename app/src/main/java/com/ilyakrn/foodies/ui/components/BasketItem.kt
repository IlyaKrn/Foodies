package com.ilyakrn.foodies.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilyakrn.foodies.R
import com.ilyakrn.foodies.domain.models.extended.ProductExtended
import com.ilyakrn.foodies.domain.models.extended.SelectedProductExtended
import com.ilyakrn.foodies.ui.getPriceFromInt

@Preview
@Composable
fun BasketItem(product: SelectedProductExtended = SelectedProductExtended(ProductExtended(1, 1, "Пицца с ветчиной и грибами 32 см", "jlkjklhnflkdfh dh dg hjdgj dhj dghj dj  d fdj d dg djg ", "1", 1, 1 ,1 ,"g" ,10.1 ,10.1 ,10.1 ,10.1 ,
    listOf() ), 1), onAdd: () -> Unit = {}, onRemove: () -> Unit = {}, onClick: () -> Unit = {}) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(129.dp)
            .padding(16.dp)
        ) {
            Image(modifier = Modifier
                .fillMaxHeight(),
                painter = painterResource(id = R.drawable.img),
                contentDescription = "image"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(modifier = Modifier
                .fillMaxSize()
            ) {
                Text(
                    text = product.product.name,
                    style = MaterialTheme.typography.labelLarge
                )
                Box(modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth(),
                ){
                    ProductCountChangerBasket(
                        count = product.count,
                        onAdd = {
                            onAdd()
                        },
                        onRemove = {
                            onRemove()
                        }
                    )
                    Column(modifier = Modifier
                        .align(Alignment.CenterEnd)
                    ){
                        Text(modifier = Modifier.align(Alignment.End),
                            text = getPriceFromInt(product.product.priceCurrent),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        if (product.product.priceOld !=-1) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(modifier = Modifier.align(Alignment.End),
                                text =  getPriceFromInt(product.product.priceOld),
                                style = MaterialTheme.typography.bodyMedium.copy(textDecoration = TextDecoration.LineThrough),
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }
                }

            }
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(MaterialTheme.colorScheme.tertiary))
    }



}