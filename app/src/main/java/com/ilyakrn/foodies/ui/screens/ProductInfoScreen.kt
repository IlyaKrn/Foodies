package com.ilyakrn.foodies.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilyakrn.foodies.R
import com.ilyakrn.foodies.data.repos.BasketRepositoryImpl
import com.ilyakrn.foodies.data.repos.CategoryRepositoryImpl
import com.ilyakrn.foodies.data.repos.ProductRepositoryImpl
import com.ilyakrn.foodies.data.repos.TagRepositoryImpl
import com.ilyakrn.foodies.domain.models.core.Tag
import com.ilyakrn.foodies.domain.models.extended.ProductExtended
import com.ilyakrn.foodies.domain.models.extended.SelectedProductExtended
import com.ilyakrn.foodies.domain.usecases.AddProductToBasketUseCase
import com.ilyakrn.foodies.domain.usecases.GetProductByIdUseCase
import com.ilyakrn.foodies.domain.usecases.RemoveProductFromBasketUseCase
import com.ilyakrn.foodies.ui.components.BottomButton
import com.ilyakrn.foodies.ui.components.ProductInfoParam

@Preview
@Composable
fun ProductInfoScreen(id: Long = -1L, onClose: () -> Unit = {}) {

    val product = remember {
        mutableStateOf<SelectedProductExtended?>(SelectedProductExtended(ProductExtended(1, 1, "JHhhlkhl", "hsd fhd dgjdjd jdjdghjdgjhgghjdgh dgj dgj djd djghjfgjd d hfgdhgdh fghdffgd", "1", 1, 1, 1, "г", 1.1, 1.1, 1.1, 1.1, listOf(
            Tag(0, "fdgsdfg")
        )), 1))
    }
    val mutableIsLoading = remember {
        mutableStateOf(true)
    }

    val productRepository = ProductRepositoryImpl()
    val tagRepository = TagRepositoryImpl()
    val basketRepository = BasketRepositoryImpl()

    GetProductByIdUseCase(basketRepository, productRepository, tagRepository, id).invoke {
        product.value = it
        mutableIsLoading.value = false
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ){
        if(product.value == null){
            if(mutableIsLoading.value){
                CircularProgressIndicator(modifier = Modifier
                    .align(Alignment.Center)
                )
            }
            else{
                Text(modifier = Modifier
                    .align(Alignment.Center),
                    text = stringResource(id = R.string.no_product_data)
                )
            }
        } else{
            Box{
                Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                ){
                    Image(modifier = Modifier
                        .fillMaxWidth()
                        .height(LocalConfiguration.current.screenWidthDp.dp),
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = "image"
                    )

                    Text(modifier = Modifier.padding(16.dp, 0.dp),
                        text = product.value!!.product.name,
                        style = MaterialTheme.typography.displaySmall
                    )

                    Text(modifier = Modifier.padding(16.dp, 0.dp),
                        text = product.value!!.product.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    Spacer(modifier = Modifier.height(16.dp))


                    Spacer(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.tertiary))
                    ProductInfoParam(param = if(product.value!!.product.measureUnit == "г") stringResource(R.string.weigt) else stringResource(R.string.volume), value = "${product.value!!.product.measure} ${product.value!!.product.measureUnit}")
                    Spacer(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.tertiary))
                    ProductInfoParam(param = stringResource(R.string.energy), value = product.value!!.product.energyPer100Grams.toString() + " " + stringResource(R.string.energy_unit))
                    Spacer(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.tertiary))
                    ProductInfoParam(param = stringResource(R.string.proteines), value = product.value!!.product.proteinsPer100Grams.toString() + " " + stringResource(R.string.proteines_unit))
                    Spacer(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.tertiary))
                    ProductInfoParam(param = stringResource(R.string.fats), value = product.value!!.product.fatsPer100Grams.toString() + " " + stringResource(R.string.fats_unit))
                    Spacer(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.tertiary))
                    ProductInfoParam(param = stringResource(R.string.carbohydrates), value = product.value!!.product.carbohydratesPer100Grams.toString() + " " + stringResource(R.string.carbohydrates_unit))
                    Spacer(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.tertiary))
                    Spacer(modifier = Modifier.height(72.dp))
                }

                val rubles = product.value!!.product.priceCurrent / 100
                val cops = product.value!!.product.priceCurrent % 100
                val copsStr = if(cops > 9) ",$cops" else ",0$cops"
                val strCopsFin = if(copsStr == ",00") "" else copsStr
                Box(modifier = Modifier.align(Alignment.BottomCenter)){
                    if(product.value!!.count != 0){
                        Box(modifier = Modifier
                            .padding(16.dp)
                            .width(200.dp)
                        ){
                            Box(modifier = Modifier
                                .shadow(4.dp)
                                .size(50.dp)
                                .align(Alignment.CenterStart)
                                .background(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.shapes.small
                                )
                                .clickable {
                                    RemoveProductFromBasketUseCase(basketRepository, id).invoke()
                                    GetProductByIdUseCase(basketRepository, productRepository, tagRepository, id).invoke {
                                        product.value = it
                                        mutableIsLoading.value = false
                                    }
                                }
                            ) {
                                Icon(modifier = Modifier.align(Alignment.Center),
                                    painter = painterResource(id = R.drawable.remove),
                                    contentDescription = "remove",
                                )
                            }
                            Text(modifier = Modifier
                                .align(Alignment.Center),
                                text = product.value!!.count.toString()
                            )
                            Box(modifier = Modifier
                                .shadow(4.dp)
                                .size(50.dp)
                                .align(Alignment.CenterEnd)
                                .background(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.shapes.small
                                )
                                .clickable {
                                    AddProductToBasketUseCase(basketRepository, id).invoke()
                                    GetProductByIdUseCase(basketRepository, productRepository, tagRepository, id).invoke {
                                        product.value = it
                                        mutableIsLoading.value = false
                                    }
                                }
                            ) {
                                Icon(modifier = Modifier.align(Alignment.Center),
                                    painter = painterResource(id = R.drawable.add),
                                    contentDescription = "remove"
                                )
                            }
                        }

                    } else{
                        BottomButton(
                            text = stringResource(R.string.add_to_basket) + " ${rubles}${strCopsFin} ₽",
                            onClick = {
                                AddProductToBasketUseCase(basketRepository, id).invoke()
                                GetProductByIdUseCase(basketRepository, productRepository, tagRepository, id).invoke {
                                    product.value = it
                                    mutableIsLoading.value = false
                                }
                            }
                        )
                    }
                }
            }
        }
        Box(modifier = Modifier
            .size(76.dp)
        ){
            Box(modifier = Modifier
                .size(40.dp)
                .shadow(8.dp)
                .align(Alignment.Center)
                .background(
                    MaterialTheme.colorScheme.background,
                    MaterialTheme.shapes.extraLarge
                )
                .clickable {
                    onClose()
                }
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "remove"
                )
            }
        }
    }

}