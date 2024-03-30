package com.ilyakrn.foodies.data.basket

import com.ilyakrn.foodies.domain.models.core.SelectedProduct

object Basket {

    private val basket: ArrayList<SelectedProduct> = ArrayList()

    fun getSelectedProductList(): List<SelectedProduct> {
        return basket
    }

    fun addSelectedProduct(product: SelectedProduct) {
        basket.add(product)
    }
    fun removeSelectedProduct(productId: Long){
        basket.removeIf { it.productId == productId }
    }
    fun editSelectedProduct(product: SelectedProduct){
        basket.removeIf { it.productId == product.productId }
        basket.add(product)
    }
}