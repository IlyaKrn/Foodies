package com.ilyakrn.foodies.data.repos

import com.ilyakrn.foodies.data.retrofit.RetrofitClient
import com.ilyakrn.foodies.data.retrofit.services.RetorfitProductService
import com.ilyakrn.foodies.data.retrofit.services.RetorfitTagService
import com.ilyakrn.foodies.domain.models.core.Product
import com.ilyakrn.foodies.domain.models.core.Tag
import com.ilyakrn.foodies.domain.repositories.ProductRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepositoryImpl : ProductRepository {
    override fun getProductList(listener: (List<Product>) -> Unit) {
        val call = RetrofitClient.getClient().create(RetorfitProductService::class.java).getProductList()

        call.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if(response.isSuccessful && response.body() != null){
                    listener(response.body()!!)
                }else{
                    listener(ArrayList())
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                listener(ArrayList())
            }
        })
    }

}