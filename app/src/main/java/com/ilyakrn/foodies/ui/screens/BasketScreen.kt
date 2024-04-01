package com.ilyakrn.foodies.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilyakrn.foodies.R
import com.ilyakrn.foodies.data.repos.BasketRepositoryImpl
import com.ilyakrn.foodies.data.repos.CategoryRepositoryImpl
import com.ilyakrn.foodies.data.repos.ProductRepositoryImpl
import com.ilyakrn.foodies.data.repos.TagRepositoryImpl
import com.ilyakrn.foodies.domain.models.extended.SelectedProductExtended
import com.ilyakrn.foodies.domain.usecases.AddProductToBasketUseCase
import com.ilyakrn.foodies.domain.usecases.GetBasketListUseCase
import com.ilyakrn.foodies.domain.usecases.GetBasketPriceUseCase
import com.ilyakrn.foodies.domain.usecases.GetProductListByCategoryUseCase
import com.ilyakrn.foodies.domain.usecases.RemoveProductFromBasketUseCase
import com.ilyakrn.foodies.ui.components.BasketItem
import com.ilyakrn.foodies.ui.components.BasketTopBar
import com.ilyakrn.foodies.ui.components.BottomButton
import com.ilyakrn.foodies.ui.components.GifImage
import com.ilyakrn.foodies.ui.components.ProductCard
import com.ilyakrn.foodies.ui.getPriceFromInt

@Preview
@Composable
fun BasketScreen(onClose: () -> Unit = {}, onShowProductInfo: (Long) -> Unit = {}) {

    val categoryRepository = CategoryRepositoryImpl()
    val productRepository = ProductRepositoryImpl()
    val tagRepository = TagRepositoryImpl()
    val basketRepository = BasketRepositoryImpl()


    val productList = remember {
        mutableStateOf(ArrayList<SelectedProductExtended>())
    }
    val mutableBasketPrice = remember {
        mutableStateOf(0)
    }
    val mutableIsLoading = remember {
        mutableStateOf(true)
    }

    GetBasketListUseCase(productRepository, tagRepository, basketRepository).invoke {
        productList.value = it as ArrayList<SelectedProductExtended>
        mutableIsLoading.value = false
    }
    GetBasketPriceUseCase(basketRepository, productRepository).invoke {
        mutableBasketPrice.value = it
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ){
        BasketTopBar(
            onClose = onClose
        )
        Box(modifier = Modifier
            .fillMaxSize()
        ){
            if(productList.value.isEmpty()) {
                if(mutableIsLoading.value){
                    CircularProgressIndicator(modifier = Modifier
                        .align(Alignment.Center)
                    )
                }
                else{
                    Text(modifier = Modifier
                        .align(Alignment.Center),
                        text = stringResource(id = R.string.no_products_in_basket)
                    )
                }
            }
            else{
                Column {
                    LazyColumn(
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 0.dp, if (mutableBasketPrice.value != 0) 72.dp else 0.dp),
                    ) {
                        items(productList.value.size) {
                            BasketItem(
                                product = productList.value[it],
                                onAdd = {
                                    AddProductToBasketUseCase(basketRepository, productList.value[it].product.id).invoke()
                                    GetProductListByCategoryUseCase(basketRepository, productRepository, tagRepository,  productList.value[it].product.categoryId).invoke {
                                        productList.value = it as ArrayList<SelectedProductExtended>
                                        mutableIsLoading.value = false
                                    }
                                    GetBasketPriceUseCase(basketRepository, productRepository).invoke {
                                        mutableBasketPrice.value = it
                                    }
                                },
                                onRemove = {
                                    RemoveProductFromBasketUseCase(basketRepository,  productList.value[it].product.id).invoke()
                                    GetProductListByCategoryUseCase(basketRepository, productRepository, tagRepository,  productList.value[it].product.categoryId).invoke {
                                        productList.value = it as ArrayList<SelectedProductExtended>
                                        mutableIsLoading.value = false
                                    }
                                    GetBasketPriceUseCase(basketRepository, productRepository).invoke {
                                        mutableBasketPrice.value = it
                                    }
                                },
                                onClick = {
                                    onShowProductInfo( productList.value[it].product.id)
                                }
                            )
                        }
                    }
                }
            }
            if(mutableBasketPrice.value != 0) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                ) {
                    BottomButton(
                        text = stringResource(R.string.order_for) + " " + getPriceFromInt(
                            mutableBasketPrice.value
                        )
                    )
                }
            }
        }
    }
}