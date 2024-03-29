package com.ilyakrn.foodies.domain.usecases

import com.ilyakrn.foodies.domain.models.core.Product
import com.ilyakrn.foodies.domain.models.core.Tag
import com.ilyakrn.foodies.domain.models.extended.ProductExtended
import com.ilyakrn.foodies.domain.models.extended.SelectedProductExtended
import com.ilyakrn.foodies.domain.repositories.BasketRepository
import com.ilyakrn.foodies.domain.repositories.ProductRepository
import com.ilyakrn.foodies.domain.repositories.TagRepository

class GetProductInBasketListUseCase(private val productRepository: ProductRepository, private val tagRepository: TagRepository, private val basketRepository: BasketRepository) {

    fun invoke(): List<SelectedProductExtended>{
        val products = basketRepository.getSelectedProductList()

        //TODO: convert ids to entities

        return ArrayList<SelectedProductExtended>()
    }

}