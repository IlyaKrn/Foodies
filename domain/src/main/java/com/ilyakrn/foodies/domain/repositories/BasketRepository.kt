package com.ilyakrn.foodies.domain.repositories

import com.ilyakrn.foodies.domain.models.core.SelectedProduct

interface BasketRepository {
    fun getSelectedProductList(): List<SelectedProduct>
    fun addSelectedProduct(product: SelectedProduct)
    fun removeSelectedProduct(id: Long)
    fun editSelectedProduct(product: SelectedProduct)
}