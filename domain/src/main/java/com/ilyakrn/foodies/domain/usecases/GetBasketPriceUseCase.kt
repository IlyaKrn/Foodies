package com.ilyakrn.foodies.domain.usecases

import com.ilyakrn.foodies.domain.models.extended.SelectedProductExtended
import com.ilyakrn.foodies.domain.repositories.BasketRepository
import com.ilyakrn.foodies.domain.repositories.ProductRepository

class GetBasketPriceUseCase(private val basketRepository: BasketRepository, private val productRepository: ProductRepository) {

    fun invoke(listener: (Int) -> Unit){
        productRepository.getProductList{products ->
            var price = 0
            basketRepository.getSelectedProductList().forEach{selectedProduct ->
                products.forEach {product ->
                    if(product.id == selectedProduct.productId)
                        price += product.priceCurrent * selectedProduct.count
                }
            }
            listener(price)
        }
    }

}