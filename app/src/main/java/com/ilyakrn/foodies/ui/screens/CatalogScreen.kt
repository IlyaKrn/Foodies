package com.ilyakrn.foodies.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import com.ilyakrn.foodies.domain.models.core.Category
import com.ilyakrn.foodies.domain.models.extended.SelectedProductExtended
import com.ilyakrn.foodies.domain.usecases.AddProductToBasketUseCase
import com.ilyakrn.foodies.domain.usecases.GetBasketPriceUseCase
import com.ilyakrn.foodies.domain.usecases.GetCategoryListUseCase
import com.ilyakrn.foodies.domain.usecases.GetProductListByCategoryUseCase
import com.ilyakrn.foodies.domain.usecases.RemoveProductFromBasketUseCase
import com.ilyakrn.foodies.ui.components.BottomButton
import com.ilyakrn.foodies.ui.components.CategoryBar
import com.ilyakrn.foodies.ui.components.CategoryCard
import com.ilyakrn.foodies.ui.components.MainTopBar
import com.ilyakrn.foodies.ui.components.ProductCard
import com.ilyakrn.foodies.ui.getPriceFromInt

//экран каталога
@Preview
@Composable
fun CatalogScreen(onShowBasket: () -> Unit = {},onShowProductInfo: (Long) -> Unit = {} ) {

    //репозитории
    val categoryRepository = CategoryRepositoryImpl()
    val productRepository = ProductRepositoryImpl()
    val tagRepository = TagRepositoryImpl()
    val basketRepository = BasketRepositoryImpl()


    //список категорий
    val categoryList = remember {
        mutableStateOf(ArrayList<Category>())
    }
    //выбранная категория
    val selectedCategory = remember {
        mutableStateOf(-1L)
    }
    //список продуктов
    val productList = remember {
        mutableStateOf(ArrayList<SelectedProductExtended>())
    }
    //цена в корзине
    val mutableBasketPrice = remember {
        mutableStateOf(0)
    }
    //загрузка данных
    val mutableIsLoading = remember {
        mutableStateOf(true)
    }

    //если не выбрана категория (пока не загрузился список категорий)
    if(selectedCategory.value == -1L) {
        //загрузка списка категорий
        GetCategoryListUseCase(categoryRepository).invoke {
            categoryList.value = it as ArrayList<Category>
            selectedCategory.value = if (it.isNotEmpty()) it[0].id else -1L
            //загрузка продуктов по категории
            GetProductListByCategoryUseCase(basketRepository, productRepository, tagRepository, it[0].id).invoke {
                productList.value = it as ArrayList<SelectedProductExtended>
                mutableIsLoading.value = false
            }
        }
    }
    //загрузка цены корзины
    GetBasketPriceUseCase(basketRepository, productRepository).invoke{
        mutableBasketPrice.value = it
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ){
        //верхняя панель
        MainTopBar()
        //список категорий
        CategoryBar(
            categories = categoryList.value,
            selectedId = selectedCategory.value,
            onSelect = {
                //обновление списка продуктов
                if(selectedCategory.value != it) {
                    productList.value.clear()
                    mutableIsLoading.value = true
                    GetProductListByCategoryUseCase(
                        basketRepository,
                        productRepository,
                        tagRepository,
                        it
                    ).invoke {
                        productList.value = it as ArrayList<SelectedProductExtended>
                        mutableIsLoading.value = false
                    }
                    selectedCategory.value = it
                }
            }
        )
        Box(modifier = Modifier
            .fillMaxSize()
        ){
            //если список продуктов пустой
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
                    //сообщение об отсутствии продуктов
                    Text(modifier = Modifier
                        .align(Alignment.Center),
                        text = stringResource(id = R.string.no_products_in_category)
                    )
                }
            }
            //если список продуктов не пустой
            else{
                Column {
                    //список продуктов
                    LazyVerticalGrid(modifier = Modifier
                        .padding(12.dp, 12.dp, 12.dp, if(mutableBasketPrice.value != 0) 72.dp else 0.dp),
                        columns = GridCells.Adaptive(((LocalConfiguration.current.screenWidthDp / 2) - 24).dp)
                    ){
                        items(productList.value){
                            ProductCard(
                                product = it,
                                onAdd = {
                                    //добавление в корзину и обновление списка
                                    AddProductToBasketUseCase(basketRepository, it.product.id).invoke()
                                    GetProductListByCategoryUseCase(basketRepository, productRepository, tagRepository, it.product.categoryId).invoke {
                                        productList.value = it as ArrayList<SelectedProductExtended>
                                        mutableIsLoading.value = false
                                    }
                                    GetBasketPriceUseCase(basketRepository, productRepository).invoke{
                                        mutableBasketPrice.value = it
                                    }
                                },
                                onRemove = {
                                    //удаление из корзины и обновление списка
                                    RemoveProductFromBasketUseCase(basketRepository, it.product.id).invoke()
                                    GetProductListByCategoryUseCase(basketRepository, productRepository, tagRepository, it.product.categoryId).invoke {
                                        productList.value = it as ArrayList<SelectedProductExtended>
                                        mutableIsLoading.value = false
                                    }
                                    GetBasketPriceUseCase(basketRepository, productRepository).invoke{
                                        mutableBasketPrice.value = it
                                    }
                                },
                                onClick = {
                                    //открыть карточку продута
                                    onShowProductInfo(it.product.id)
                                }
                            )
                        }
                    }
                }
            }
            //если корзина не пуста
            if(mutableBasketPrice.value != 0){
                //кнопка открытия корзины
                Box(modifier = Modifier

                .align(Alignment.BottomCenter)
                ) {
                    BottomButton(text = getPriceFromInt(mutableBasketPrice.value), onClick = onShowBasket, iconId = R.drawable.basket)
                }
            }
        }
    }
}
