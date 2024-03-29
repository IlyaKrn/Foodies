package com.ilyakrn.foodies.domain.usecases

import com.ilyakrn.foodies.domain.models.core.Product
import com.ilyakrn.foodies.domain.models.core.Tag
import com.ilyakrn.foodies.domain.models.extended.ProductExtended
import com.ilyakrn.foodies.domain.repositories.BasketRepository
import com.ilyakrn.foodies.domain.repositories.ProductRepository
import com.ilyakrn.foodies.domain.repositories.TagRepository

class GetSelectedProductCountInBasketUseCase(private val basketRepository: BasketRepository) {

    fun invoke(): Int{
        val products = basketRepository.getSelectedProductList()
        var count = 0
        products.forEach{
            count += it.count
        }
        return count
    }

}