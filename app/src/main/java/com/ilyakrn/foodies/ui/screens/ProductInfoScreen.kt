package com.ilyakrn.foodies.ui.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import com.ilyakrn.foodies.ui.components.ProductCountChangerInfo
import com.ilyakrn.foodies.ui.components.ProductInfoParam
import com.ilyakrn.foodies.ui.components.ProductProperties
import com.ilyakrn.foodies.ui.getPriceFromInt

@Preview
@Composable
fun ProductInfoScreen(id: Long = -1L, onClose: () -> Unit = {}) {

    val product = remember {
        mutableStateOf<SelectedProductExtended?>(null)
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

                Box(modifier = Modifier.align(Alignment.BottomCenter)){

                    AnimatedVisibility(modifier = Modifier.align(Alignment.BottomCenter),
                        visible = product.value!!.count == 0,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        BottomButton(
                            text = stringResource(R.string.add_to_basket) + " " + getPriceFromInt(product.value!!.product.priceCurrent),
                            onClick = {
                                if(product.value!!.count == 0) {
                                    AddProductToBasketUseCase(basketRepository, id).invoke()
                                    GetProductByIdUseCase(
                                        basketRepository,
                                        productRepository,
                                        tagRepository,
                                        id
                                    ).invoke {
                                        product.value = it
                                        mutableIsLoading.value = false
                                    }
                                }
                            }
                        )
                    }
                    AnimatedVisibility(modifier = Modifier.align(Alignment.BottomCenter),
                        visible = product.value!!.count != 0,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        ProductCountChangerInfo(
                            onAdd = {
                                if(product.value!!.count != 0) {
                                    AddProductToBasketUseCase(basketRepository, id).invoke()
                                    GetProductByIdUseCase(
                                        basketRepository,
                                        productRepository,
                                        tagRepository,
                                        id
                                    ).invoke {
                                        product.value = it
                                        mutableIsLoading.value = false
                                    }
                                }},
                            onRemove = {
                                if(product.value!!.count != 0) {
                                    RemoveProductFromBasketUseCase(basketRepository, id).invoke()
                                    GetProductByIdUseCase(basketRepository, productRepository, tagRepository, id).invoke {
                                        product.value = it
                                        mutableIsLoading.value = false
                                    }
                                }},
                            count = product.value!!.count
                        )
                    }
                }
            }
        }
        Column(modifier = Modifier
            .width(76.dp)
            .padding(16.dp)
        ){
            Box(modifier = Modifier
                .size(40.dp)
                .shadow(8.dp)
                .background(MaterialTheme.colorScheme.background, MaterialTheme.shapes.extraLarge)
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
            var spicy = false
            var vegetarian = false
            product.value!!.product.tags.forEach {
                if (it.id == 2L)
                    vegetarian = true
                if (it.id == 4L)
                    spicy = true
            }
            ProductProperties(discount = product.value!!.product.priceOld != -1, spicy = spicy, vegetarian = vegetarian)
        }
    }

}