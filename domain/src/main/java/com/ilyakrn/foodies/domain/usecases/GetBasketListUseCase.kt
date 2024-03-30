package com.ilyakrn.foodies.domain.usecases

import com.ilyakrn.foodies.domain.models.core.Product
import com.ilyakrn.foodies.domain.models.core.Tag
import com.ilyakrn.foodies.domain.models.extended.ProductExtended
import com.ilyakrn.foodies.domain.models.extended.SelectedProductExtended
import com.ilyakrn.foodies.domain.repositories.BasketRepository
import com.ilyakrn.foodies.domain.repositories.ProductRepository
import com.ilyakrn.foodies.domain.repositories.TagRepository

class GetBasketListUseCase(private val productRepository: ProductRepository, private val tagRepository: TagRepository, private val basketRepository: BasketRepository) {

    fun invoke(): List<SelectedProductExtended>{
        val result = ArrayList<SelectedProductExtended>()

        val selectedProducts = basketRepository.getSelectedProductList()
        val productTags = tagRepository.getTagList()
        val products = productRepository.getProductList()
        selectedProducts.forEach {selectedProduct ->
            var product: Product? = null
            products.stream().forEach {
                if(selectedProduct.productId == it.id){
                    product = it;
                }
            }
            product?.let {
                val tags = ArrayList<Tag>()
                productTags.forEach { tag->
                    it.tagIds.forEach{tagId ->
                        if(tag.id == tagId)
                            tags.add(tag);
                    }
                }
                val productExtended =
                    ProductExtended(
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
                    productTags)
                result.add(SelectedProductExtended(productExtended, selectedProduct.count))
            }
        }

        return result
    }

}