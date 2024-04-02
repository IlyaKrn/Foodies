package com.ilyakrn.foodies.domain.usecases

import android.util.Log
import com.ilyakrn.foodies.domain.models.core.Category
import com.ilyakrn.foodies.domain.models.core.Tag
import com.ilyakrn.foodies.domain.models.extended.ProductExtended
import com.ilyakrn.foodies.domain.models.extended.SelectedProductExtended
import com.ilyakrn.foodies.domain.repositories.BasketRepository
import com.ilyakrn.foodies.domain.repositories.ProductRepository
import com.ilyakrn.foodies.domain.repositories.TagRepository

//получение продукта по id
class GetProductByIdUseCase(private val basketRepository: BasketRepository, private val productRepository: ProductRepository, private val tagRepository: TagRepository, private val productId: Long) {

    fun invoke(listener: (SelectedProductExtended?) -> Unit){
        productRepository.getProductList{products ->
            tagRepository.getTagList{tags ->
                var p: SelectedProductExtended? = null
                products.forEach {product ->
                    if(product.id == productId) {
                        val productTags = ArrayList<Tag>()
                        tags.forEach { tag ->
                            product.tagIds.forEach { tagId ->
                                if (tag.id == tagId)
                                    productTags.add(tag);
                            }
                        }
                        var count = 0;
                        basketRepository.getSelectedProductList().forEach { selectedProduct ->
                            if(selectedProduct.productId == productId)
                                count = selectedProduct.count
                        }
                        p =  SelectedProductExtended(
                            ProductExtended(
                                product.id,
                                product.categoryId,
                                product.name,
                                product.description,
                                product.image,
                                product.priceCurrent,
                                product.priceOld,
                                product.measure,
                                product.measureUnit,
                                product.energyPer100Grams,
                                product.proteinsPer100Grams,
                                product.fatsPer100Grams,
                                product.carbohydratesPer100Grams,
                                productTags
                            ),
                            count
                        )
                    }
                }
                listener(p)
            }

        }
    }

}