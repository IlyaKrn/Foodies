package com.ilyakrn.foodies.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilyakrn.foodies.R
import com.ilyakrn.foodies.domain.models.core.Category
import com.ilyakrn.foodies.domain.models.core.Product
import com.ilyakrn.foodies.domain.models.core.SelectedProduct
import com.ilyakrn.foodies.domain.models.core.Tag
import com.ilyakrn.foodies.domain.repositories.BasketRepository
import com.ilyakrn.foodies.domain.repositories.CategoryRepository
import com.ilyakrn.foodies.domain.repositories.ProductRepository
import com.ilyakrn.foodies.domain.repositories.TagRepository
import com.ilyakrn.foodies.domain.usecases.GetBasketPriceUseCase
import com.ilyakrn.foodies.domain.usecases.GetCategoryListUseCase
import com.ilyakrn.foodies.domain.usecases.GetProductListByCategoryUseCase
import com.ilyakrn.foodies.ui.components.ProductCard
import java.util.Collections

@Preview
@Composable
fun CatalogScreen() {


    val productRepository = object : ProductRepository{
        override fun getProductList(): List<Product> {
            val res = ArrayList<Product>()
            res.add(Product(0, 0, "Name", "Description", "Image", 10000, 0, 250, "g", 10.1, 10.1, 10.1, 10.1, Collections.emptyList()))
            res.add(Product(1, 0, "Name", "Description", "Image", 10000, 0, 250, "g", 10.1, 10.1, 10.1, 10.1, Collections.emptyList()))
            res.add(Product(2, 0, "Name", "Description", "Image", 10000, 0, 250, "g", 10.1, 10.1, 10.1, 10.1, Collections.emptyList()))
            res.add(Product(3, 0, "Name", "Description", "Image", 10000, 0, 250, "g", 10.1, 10.1, 10.1, 10.1, Collections.emptyList()))
            res.add(Product(4, 0, "Name", "Description", "Image", 10000, 0, 250, "g", 10.1, 10.1, 10.1, 10.1, Collections.emptyList()))
            res.add(Product(5, 0, "Name", "Description", "Image", 10000, 0, 250, "g", 10.1, 10.1, 10.1, 10.1, Collections.emptyList()))
            res.add(Product(6, 0, "Name", "Description", "Image", 10000, 0, 250, "g", 10.1, 10.1, 10.1, 10.1, Collections.emptyList()))
            res.add(Product(7, 0, "Name", "Description", "Image", 10000, 0, 250, "g", 10.1, 10.1, 10.1, 10.1, Collections.emptyList()))
            res.add(Product(8, 0, "Name", "Description", "Image", 10000, 0, 250, "g", 10.1, 10.1, 10.1, 10.1, Collections.emptyList()))
            res.add(Product(9, 0, "Name", "Description", "Image", 10000, 0, 250, "g", 10.1, 10.1, 10.1, 10.1, Collections.emptyList()))

            return res
        }
    }

    val tagRepository = object : TagRepository {
        override fun getTagList(): List<Tag> {
            val res = ArrayList<Tag>()
            res.add(Tag(0, "Tag 0"))
            res.add(Tag(1, "Tag 1"))
            res.add(Tag(2, "Tag 2"))
            res.add(Tag(3, "Tag 3"))
            res.add(Tag(4, "Tag 4"))
            res.add(Tag(5, "Tag 5"))

            return res
        }
    }

    val categoryRepository = object : CategoryRepository{
        override fun getCategoryList(): List<Category> {
            val res = ArrayList<Category>()
            res.add(Category(0, "Name 0"))
            res.add(Category(1, "Name 1"))
            res.add(Category(2, "Name 2"))
            res.add(Category(3, "Name 3"))
            res.add(Category(4, "Name 4"))
            res.add(Category(5, "Name 5"))

            return res
        }

    }

    val basketRepository = object : BasketRepository{

        override fun getSelectedProductList(): List<SelectedProduct> {
            val res = ArrayList<SelectedProduct>()
            res.add(SelectedProduct(0, 2))
            res.add(SelectedProduct(1, 2))
            res.add(SelectedProduct(2, 2))
            res.add(SelectedProduct(3, 2))
            res.add(SelectedProduct(4, 2))
            res.add(SelectedProduct(5, 2))
            return res
        }

        override fun addSelectedProduct(product: SelectedProduct) {
           // TODO("Not yet implemented")
        }

        override fun removeSelectedProduct(id: Long) {
          //  TODO("Not yet implemented")
        }

        override fun editSelectedProduct(product: SelectedProduct) {
           // TODO("Not yet implemented")
        }

    }


    val getCategoryListUseCase = GetCategoryListUseCase(categoryRepository)

    val categories = getCategoryListUseCase.invoke()

    val mutableSelectedCategory = remember {
        mutableStateOf(if(categories.size != 0) categories.get(0).id else -1)
    }

    val getBasketPriceUseCase = GetBasketPriceUseCase(basketRepository, productRepository)
    val mutablePrice = remember {
        mutableStateOf(getBasketPriceUseCase.invoke())
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ){
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            ){
            Icon(modifier = Modifier
                .padding(18.dp)
                .align(Alignment.CenterStart),
                painter = painterResource(id = R.drawable.settings),
                tint = MaterialTheme.colorScheme.secondary,
                contentDescription = "settings"
            )
            Icon(modifier = Modifier
                .align(Alignment.Center),
                painter = painterResource(id = R.drawable.logo_top_bar),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "settings"
            )
            Icon(modifier = Modifier
                .padding(18.dp)
                .align(Alignment.CenterEnd),
                painter = painterResource(id = R.drawable.search),
                tint = MaterialTheme.colorScheme.secondary,
                contentDescription = "settings"
            )
        }
        Row{
            Spacer(modifier = Modifier.width(16.dp))
            LazyRow(modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
            ){
                items(items = getCategoryListUseCase.invoke(), itemContent = { item ->
                    if(mutableSelectedCategory.value == item.id) {
                        Box(
                            modifier = Modifier
                                .background(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.shapes.small
                                )
                                .height(60.dp)
                                .padding(16.dp, 0.dp)
                                .clickable {
                                    mutableSelectedCategory.value = item.id
                                }
                        ) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.Center),
                                text = item.name,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    } else{
                        Spacer(modifier = Modifier.width(16.dp))
                        Box(
                            modifier = Modifier
                                .height(60.dp)
                                .clickable {
                                    mutableSelectedCategory.value = item.id
                                }
                        ) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.Center),
                                text = item.name,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                })
            }
        }
        Box(modifier = Modifier
            .padding(16.dp)
        ){
            val getProductListByCategoryUseCase = GetProductListByCategoryUseCase(productRepository, tagRepository, mutableSelectedCategory.value)
            LazyVerticalGrid(columns = GridCells.Adaptive(170.dp)){
                items(getProductListByCategoryUseCase.invoke()){
                    ProductCard(it)
                }
            }
            if(mutablePrice.value != 0){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.BottomCenter)
                    .background(MaterialTheme.colorScheme.background)
                    .clickable {

                    }
                ) {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .align(Alignment.BottomCenter)
                        .background(MaterialTheme.colorScheme.primary, MaterialTheme.shapes.small)
                        .clickable {

                        }
                    ) {
                        Text(modifier = Modifier
                            .align(Alignment.Center),
                            text = mutablePrice.value.toString() + " ₽",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}
