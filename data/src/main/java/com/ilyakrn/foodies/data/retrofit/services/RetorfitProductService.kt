package com.ilyakrn.foodies.data.retrofit.services

import com.ilyakrn.foodies.data.retrofit.GET_PRODUCT_LIST
import com.ilyakrn.foodies.domain.models.core.Product
import retrofit2.Call
import retrofit2.http.GET

interface RetorfitProductService {
    @GET(GET_PRODUCT_LIST)
    fun getProductList(): Call<List<Product>>
}