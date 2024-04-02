package com.ilyakrn.foodies.domain.models.extended

//модель продукта в корзине в расширенным продуктом вместо id продукта
data class SelectedProductExtended(
    val product: ProductExtended,
    val count: Int
)