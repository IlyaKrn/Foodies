package com.ilyakrn.foodies.domain.usecases

import com.ilyakrn.foodies.domain.repositories.BasketRepository
import com.ilyakrn.foodies.domain.repositories.ProductRepository

class GetBasketPriceUseCase(private val basketRepository: BasketRepository, private val productRepository: ProductRepository) {

    fun invoke(): Int{
        val selectedProducts = basketRepository.getSelectedProductList()
        val products = productRepository.getProductList()
        var price = 0
        selectedProducts.forEach{selectedProduct ->
            products.forEach {product ->
                if(product.id == selectedProduct.productId)
                    price += product.priceCurrent
            }
        }
        return price
    }

}