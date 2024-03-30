package com.ilyakrn.foodies.domain.usecases

import com.ilyakrn.foodies.domain.repositories.BasketRepository

class GetBasketSizeUseCase(private val basketRepository: BasketRepository) {

    fun invoke(): Int{
        val products = basketRepository.getSelectedProductList()
        var count = 0
        products.forEach{
            count += it.count
        }
        return count
    }

}