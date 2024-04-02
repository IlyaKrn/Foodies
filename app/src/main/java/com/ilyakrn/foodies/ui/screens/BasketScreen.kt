package com.ilyakrn.foodies.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.ilyakrn.foodies.domain.usecases.RemoveProductFromBasketUseCase
import com.ilyakrn.foodies.ui.components.BasketItem
import com.ilyakrn.foodies.ui.components.BasketTopBar
import com.ilyakrn.foodies.ui.components.BottomButton
import com.ilyakrn.foodies.ui.getPriceFromInt

//экран корзины
@Preview
@Composable
fun BasketScreen(onClose: () -> Unit = {}, onShowProductInfo: (Long) -> Unit = {}) {

    //репозитории
    val categoryRepository = CategoryRepositoryImpl()
    val productRepository = ProductRepositoryImpl()
    val tagRepository = TagRepositoryImpl()
    val basketRepository = BasketRepositoryImpl()


    //список продуктов
    val productList = remember {
        mutableStateOf(ArrayList<SelectedProductExtended>())
    }
    //цена корзины
    val mutableBasketPrice = remember {
        mutableStateOf(0)
    }
    //идет ли загрузка
    val mutableIsLoading = remember {
        mutableStateOf(true)
    }

    //загрузка списка продуктов
    GetBasketListUseCase(productRepository, tagRepository, basketRepository).invoke {
        productList.value = it as ArrayList<SelectedProductExtended>
        mutableIsLoading.value = false
    }
    //загрузка цены корзины
    GetBasketPriceUseCase(basketRepository, productRepository).invoke {
        mutableBasketPrice.value = it
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ){
        //верхняя панель
        BasketTopBar(
            onClose = onClose
        )
        Box(modifier = Modifier
            .fillMaxSize()
        ){
            //если список пуст
            if(productList.value.isEmpty()) {
                //если идет загрузка
                if(mutableIsLoading.value){
                    //прогресс бар
                    CircularProgressIndicator(modifier = Modifier
                        .align(Alignment.Center)
                    )
                }
                //если загрузка завершена
                else{
                    //сообщение об отсутствии продуктов в корзине
                    Text(modifier = Modifier
                        .align(Alignment.Center),
                        text = stringResource(id = R.string.no_products_in_basket)
                    )
                }
            }
            //если список не пуст
            else{
                Column {
                    LazyColumn(
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 0.dp, if (mutableBasketPrice.value != 0) 72.dp else 0.dp),
                    ) {
                        items(productList.value){
                            BasketItem(
                                product = it,
                                onAdd = {
                                    //увеличение колличества и обновление данных
                                    AddProductToBasketUseCase(basketRepository, it.product.id).invoke()
                                    GetBasketListUseCase(productRepository, tagRepository, basketRepository).invoke {
                                        mutableIsLoading.value = false
                                        productList.value = it as ArrayList<SelectedProductExtended>
                                    }
                                    GetBasketPriceUseCase(basketRepository, productRepository).invoke {
                                        mutableBasketPrice.value = it
                                    }
                                },
                                onRemove = {
                                    //уменьшение колличества и обновление данных
                                    RemoveProductFromBasketUseCase(basketRepository,  it.product.id).invoke()
                                    GetBasketListUseCase(productRepository, tagRepository, basketRepository).invoke {
                                        mutableIsLoading.value = false
                                        productList.value = it as ArrayList<SelectedProductExtended>
                                    }
                                    GetBasketPriceUseCase(basketRepository, productRepository).invoke {
                                        mutableBasketPrice.value = it
                                    }
                                },
                                onClick = {
                                    //открыть карточку продукта
                                    onShowProductInfo(it.product.id)
                                }
                            )
                        }
                    }
                }
            }
            //если корзина не пуста
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