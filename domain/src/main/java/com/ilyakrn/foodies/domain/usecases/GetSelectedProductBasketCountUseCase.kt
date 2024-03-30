package com.ilyakrn.foodies.domain.usecases

import com.ilyakrn.foodies.domain.repositories.BasketRepository
import com.ilyakrn.foodies.domain.repositories.ProductRepository

class GetSelectedProductBasketCountUseCase(private val basketRepository: BasketRepository, private val productId: Long) {

    fun invoke(): Int{
        val selectedProducts = basketRepository.getSelectedProductList()
        var count = 0
        selectedProducts.forEach{selectedProduct ->
            if(selectedProduct.productId == productId)
                count = selectedProduct.count
        }
        return count
    }

}