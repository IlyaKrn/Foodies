package com.ilyakrn.foodies.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilyakrn.foodies.R
import com.ilyakrn.foodies.data.repos.BasketRepositoryImpl
import com.ilyakrn.foodies.data.repos.CategoryRepositoryImpl
import com.ilyakrn.foodies.data.repos.ProductRepositoryImpl
import com.ilyakrn.foodies.data.repos.TagRepositoryImpl
import com.ilyakrn.foodies.domain.models.core.Category
import com.ilyakrn.foodies.domain.models.core.Product
import com.ilyakrn.foodies.domain.models.core.SelectedProduct
import com.ilyakrn.foodies.domain.models.core.Tag
import com.ilyakrn.foodies.domain.models.extended.ProductExtended
import com.ilyakrn.foodies.domain.models.extended.SelectedProductExtended
import com.ilyakrn.foodies.domain.repositories.BasketRepository
import com.ilyakrn.foodies.domain.repositories.CategoryRepository
import com.ilyakrn.foodies.domain.repositories.ProductRepository
import com.ilyakrn.foodies.domain.repositories.TagRepository
import com.ilyakrn.foodies.domain.usecases.GetSelectedProductBasketCountUseCase
import java.util.Collections

@Composable
fun ProductCard(product: SelectedProductExtended, onAdd: () -> Unit = {}, onRemove: () -> Unit = {}, onClick: () -> Unit = {}) {

    Box(modifier = Modifier
        .padding(4.dp)
    ){

        Column(modifier = Modifier
            .width(170.dp)
            .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.small)
            .clickable { onClick() }
        ){
            Image(modifier = Modifier
                .height(170.dp)
                .width(170.dp),
                painter = painterResource(id = R.drawable.img),
                contentDescription = "image"
            )
            Column(modifier = Modifier
                .padding(16.dp)
            ){
                Text(
                    text = product.product.name,
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${product.product.measure} ${product.product.measureUnit}",
                    style = MaterialTheme.typography.bodySmall,
                )
                Spacer(modifier = Modifier.height(8.dp))
                if(product.count == 0) {
                    Box(modifier = Modifier
                        .shadow(4.dp)
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.shapes.small
                        )
                        .padding(8.dp)
                        .clickable {
                            onAdd()
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .align(Alignment.Center)
                        ) {
                            val rubles = product.product.priceCurrent / 100
                            val cops = product.product.priceCurrent % 100
                            val copsStr = if(cops > 9) ",$cops" else ",0$cops"
                            val strCopsFin = if(copsStr == ",00") "" else copsStr
                            Text(
                                text = "${rubles}${strCopsFin} ₽",
                            )
                            if (product.product.priceOld !=-1) {
                                Spacer(modifier = Modifier.width(8.dp))
                                val rublesOld = product.product.priceOld / 100
                                val copsOld = product.product.priceOld % 100
                                val copsStrOld = if(copsOld > 9) ",$copsOld" else ",0$copsOld"
                                val strCopsFinOld = if(copsStrOld == ",00") "" else copsStrOld
                                Text(
                                    text = "${rublesOld}${strCopsFinOld} ₽",
                                    style = MaterialTheme.typography.bodySmall.copy(textDecoration = TextDecoration.LineThrough)
                                )
                            }
                        }
                    }
                }
                else {
                    Box(modifier = Modifier.fillMaxWidth()){
                        Box(modifier = Modifier
                            .shadow(4.dp)
                            .size(40.dp)
                            .align(Alignment.CenterStart)
                            .background(
                                MaterialTheme.colorScheme.background,
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
                        Text(modifier = Modifier
                            .align(Alignment.Center),
                            text = product.count.toString()
                        )
                        Box(modifier = Modifier
                            .shadow(4.dp)
                            .size(40.dp)
                            .align(Alignment.CenterEnd)
                            .background(
                                MaterialTheme.colorScheme.background,
                                MaterialTheme.shapes.small
                            )
                            .clickable {
                                onAdd()
                            }
                        ) {
                            Icon(modifier = Modifier.align(Alignment.Center),
                                painter = painterResource(id = R.drawable.add),
                                contentDescription = "remove",
                            )
                        }
                    }

                }


            }
        }
    }


}