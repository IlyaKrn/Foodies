package com.ilyakrn.foodies.data.repos

import android.util.Log
import com.ilyakrn.foodies.data.retrofit.RetrofitClient
import com.ilyakrn.foodies.data.retrofit.models.CategoryJsonModel
import com.ilyakrn.foodies.data.retrofit.services.RetorfitCategoryService
import com.ilyakrn.foodies.data.retrofit.services.RetorfitProductService
import com.ilyakrn.foodies.domain.models.core.Category
import com.ilyakrn.foodies.domain.models.core.Product
import com.ilyakrn.foodies.domain.repositories.CategoryRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRepositoryImpl : CategoryRepository {
    override fun getCategoryList(listener: (List<Category>) -> Unit) {
        val call = RetrofitClient.getClient().create(RetorfitCategoryService::class.java).getCategoryList()

        call.enqueue(object : Callback<List<CategoryJsonModel>> {
            override fun onResponse(call: Call<List<CategoryJsonModel>>, response: Response<List<CategoryJsonModel>>) {
                if(response.isSuccessful && response.body() != null){
                    val res = ArrayList<Category>()
                    response.body()!!.forEach {
                        res.add(Category(it.id, it.name))
                    }
                    listener(res)
                }else{
                    listener(ArrayList())
                }
            }

            override fun onFailure(call: Call<List<CategoryJsonModel>>, t: Throwable) {
                listener(ArrayList())
            }
        })
    }

}