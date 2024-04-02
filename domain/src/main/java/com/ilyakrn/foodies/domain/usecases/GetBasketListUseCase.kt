package com.ilyakrn.foodies.domain.usecases

import android.util.Log
import com.ilyakrn.foodies.domain.models.core.Product
import com.ilyakrn.foodies.domain.models.core.Tag
import com.ilyakrn.foodies.domain.models.extended.ProductExtended
import com.ilyakrn.foodies.domain.models.extended.SelectedProductExtended
import com.ilyakrn.foodies.domain.repositories.BasketRepository
import com.ilyakrn.foodies.domain.repositories.ProductRepository
import com.ilyakrn.foodies.domain.repositories.TagRepository

//получение списка продуктов в корзине
class GetBasketListUseCase(private val productRepository: ProductRepository, private val tagRepository: TagRepository, private val basketRepository: BasketRepository) {

    fun invoke(listener: (List<SelectedProductExtended>) -> Unit){
        tagRepository.getTagList{productTags ->
            productRepository.getProductList{products ->
                val result = ArrayList<SelectedProductExtended>()
                basketRepository.getSelectedProductList().forEach { selectedProduct ->
                    products.stream().forEach {
                        if(selectedProduct.productId == it.id){
                            val tags = ArrayList<Tag>()
                            productTags.forEach { tag->
                                it.tagIds.forEach{tagId ->
                                    if(tag.id == tagId)
                                        tags.add(tag);
                                }
                            }
                            val productExtended = ProductExtended(
                                it.id,
                                it.categoryId,
                                it.name,
                                it.description,
                                it.image,
                                it.priceCurrent,
                                it.priceOld,
                                it.measure,
                                it.measureUnit,
                                it.energyPer100Grams,
                                it.proteinsPer100Grams,
                                it.fatsPer100Grams,
                                it.carbohydratesPer100Grams,
                                tags)
                            result.add(SelectedProductExtended(productExtended, selectedProduct.count))
                        }
                    }
                }
                listener(result)
            }

        }
    }

}