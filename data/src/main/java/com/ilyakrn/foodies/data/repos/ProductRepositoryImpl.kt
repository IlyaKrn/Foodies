package com.ilyakrn.foodies.data.repos

import com.ilyakrn.foodies.data.retrofit.RetrofitClient
import com.ilyakrn.foodies.data.retrofit.models.ProductJsonModel
import com.ilyakrn.foodies.data.retrofit.services.RetorfitProductService
import com.ilyakrn.foodies.data.retrofit.services.RetorfitTagService
import com.ilyakrn.foodies.domain.models.core.Category
import com.ilyakrn.foodies.domain.models.core.Product
import com.ilyakrn.foodies.domain.models.core.Tag
import com.ilyakrn.foodies.domain.repositories.ProductRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepositoryImpl : ProductRepository {
    override fun getProductList(listener: (List<Product>) -> Unit) {
        val call = RetrofitClient.getClient().create(RetorfitProductService::class.java).getProductList()

        call.enqueue(object : Callback<List<ProductJsonModel>> {
            override fun onResponse(call: Call<List<ProductJsonModel>>, response: Response<List<ProductJsonModel>>) {
                if(response.isSuccessful && response.body() != null){
                    val res = ArrayList<Product>()
                    response.body()!!.forEach {
                        res.add(Product(
                        it.id,
                        it.category_id,
                        it.name,
                        it.description,
                        it.image,
                        it.price_current,
                        it.price_old,
                        it.measure,
                        it.measure_unit,
                        it.energy_per_100_grams,
                        it.proteins_per_100_grams,
                        it.fats_per_100_grams,
                        it.carbohydrates_per_100_grams,
                        it.tag_ids))
                    }
                    listener(res)
                }else{
                    listener(ArrayList())
                }
            }

            override fun onFailure(call: Call<List<ProductJsonModel>>, t: Throwable) {
                listener(ArrayList())
            }
        })
    }

}