package com.ilyakrn.foodies.domain.usecases

import com.ilyakrn.foodies.domain.models.core.Product
import com.ilyakrn.foodies.domain.models.core.Tag
import com.ilyakrn.foodies.domain.models.extended.ProductExtended
import com.ilyakrn.foodies.domain.repositories.ProductRepository
import com.ilyakrn.foodies.domain.repositories.TagRepository

class GetProductListUseCase(private val productRepository: ProductRepository, private val tagRepository: TagRepository) {

    fun invoke(): List<ProductExtended>{
        val products = productRepository.getProductList()
        val tags = tagRepository.getTagList()
        val result = ArrayList<ProductExtended>()
        products.forEach {product ->
            val productTags = ArrayList<Tag>()
            tags.forEach { tag->
                product.tagIds.forEach{tagId ->
                    if(tag.id == tagId)
                        productTags.add(tag);
                }
            }
            result.add(ProductExtended(
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
                productTags)
            )
        }
        return result
    }

}