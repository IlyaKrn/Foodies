package com.ilyakrn.foodies.data.repos

import com.ilyakrn.foodies.data.basket.Basket
import com.ilyakrn.foodies.domain.models.core.SelectedProduct
import com.ilyakrn.foodies.domain.repositories.BasketRepository

//репозиторий корзины
class BasketRepositoryImpl() : BasketRepository {
    override fun getSelectedProductList(): List<SelectedProduct>{
        return Basket.getSelectedProductList()
    }
    override fun addSelectedProduct(product: SelectedProduct){
        Basket.addSelectedProduct(product)
    }
    override fun removeSelectedProduct(id: Long){
        Basket.removeSelectedProduct(id)
    }
    override fun editSelectedProduct(product: SelectedProduct){
        Basket.editSelectedProduct(product)
    }
}