package com.ilyakrn.foodies.data.repos

import com.ilyakrn.foodies.data.retrofit.RetrofitClient
import com.ilyakrn.foodies.data.retrofit.services.RetorfitTagService
import com.ilyakrn.foodies.domain.models.core.Tag
import com.ilyakrn.foodies.domain.repositories.TagRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TagRepositoryImpl : TagRepository {
    override fun getTagList(listener: (List<Tag>) -> Unit) {
        val call = RetrofitClient.getClient().create(RetorfitTagService::class.java).getTagList()

        call.enqueue(object : Callback<List<Tag>> {
            override fun onResponse(call: Call<List<Tag>>, response: Response<List<Tag>>) {
                if(response.isSuccessful && response.body() != null){
                    listener(response.body()!!)
                }else{
                    listener(ArrayList())
                }
            }

            override fun onFailure(call: Call<List<Tag>>, t: Throwable) {
                listener(ArrayList())
            }
        })
    }
}