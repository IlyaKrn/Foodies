package com.ilyakrn.foodies.domain.usecases

import com.ilyakrn.foodies.domain.models.core.Product
import com.ilyakrn.foodies.domain.models.core.SelectedProduct
import com.ilyakrn.foodies.domain.models.core.Tag
import com.ilyakrn.foodies.domain.models.extended.ProductExtended
import com.ilyakrn.foodies.domain.repositories.BasketRepository
import com.ilyakrn.foodies.domain.repositories.ProductRepository
import com.ilyakrn.foodies.domain.repositories.TagRepository

class EditProductCountInBasketUseCase(private val basketRepository: BasketRepository, private val productId: Long, private val count: Int) {

    fun invoke() {
        basketRepository.editSelectedProduct(SelectedProduct(productId, count))
    }

}