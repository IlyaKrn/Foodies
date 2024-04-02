package com.ilyakrn.foodies.data.basket

import com.ilyakrn.foodies.domain.models.core.SelectedProduct

//синглтон карзины
object Basket {

    private val basket: ArrayList<SelectedProduct> = ArrayList()

    //получение списка
    fun getSelectedProductList(): List<SelectedProduct> {
        return basket
    }
    //добавленире в список
    fun addSelectedProduct(product: SelectedProduct) {
        basket.add(product)
    }
    //удаление из списка
    fun removeSelectedProduct(productId: Long){
        basket.removeIf { it.productId == productId }
    }
    //изменение элемента спискаа по id
    fun editSelectedProduct(product: SelectedProduct){
        basket.forEach {
            if(it.productId == product.productId)
                it.count = product.count
        }
    }
}