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
import com.ilyakrn.foodies.domain.models.core.Category
import com.ilyakrn.foodies.domain.models.core.Product
import com.ilyakrn.foodies.domain.models.core.SelectedProduct
import com.ilyakrn.foodies.domain.models.core.Tag
import com.ilyakrn.foodies.domain.models.extended.ProductExtended
import com.ilyakrn.foodies.domain.repositories.BasketRepository
import com.ilyakrn.foodies.domain.repositories.CategoryRepository
import com.ilyakrn.foodies.domain.repositories.ProductRepository
import com.ilyakrn.foodies.domain.repositories.TagRepository
import com.ilyakrn.foodies.domain.usecases.GetSelectedProductBasketCountUseCase
import java.util.Collections

@Preview
@Composable
fun ProductCard(product: ProductExtended = ProductExtended(0, 0, "Name", "Description", "Image", 10000, 0, 250, "g", 10.1, 10.1, 10.1, 10.1, Collections.emptyList())) {


    val productRepository = object : ProductRepository {
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

    val categoryRepository = object : CategoryRepository {
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

    val basketRepository = object : BasketRepository {

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

    Column(modifier = Modifier
        .width(170.dp)
        .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.small)
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
                text = product.name,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.measure.toString() + " " + product.measureUnit,
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.height(8.dp))
            val count = GetSelectedProductBasketCountUseCase(basketRepository, product.id).invoke()
            if(count == 0) {
                Box(modifier = Modifier
                    .shadow(4.dp)
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.shapes.small
                    )
                    .padding(8.dp)
                    .clickable {

                    }
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.Center)
                    ) {
                        Text(
                            text = product.priceCurrent.toString() + " ₽"
                        )
                        if (product.priceOld != 0) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = product.priceCurrent.toString() + " ₽",
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
                        .background(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.shapes.small
                        )
                        .clickable {

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