package com.ilyakrn.foodies.domain.repositories

import com.ilyakrn.foodies.domain.models.core.Product

interface ProductRepository {
    fun getProductList(): List<Product>;
}