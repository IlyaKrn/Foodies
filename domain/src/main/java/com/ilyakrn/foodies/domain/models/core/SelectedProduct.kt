package com.ilyakrn.foodies.domain.models.core

//модель продукта в корзине
data class SelectedProduct(
    val productId: Long,
    var count: Int
)