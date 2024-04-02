package com.ilyakrn.foodies.data.repos

import com.ilyakrn.foodies.data.retrofit.RetrofitClient
import com.ilyakrn.foodies.data.retrofit.models.TagJsonModel
import com.ilyakrn.foodies.data.retrofit.services.RetorfitTagService
import com.ilyakrn.foodies.domain.models.core.Category
import com.ilyakrn.foodies.domain.models.core.Tag
import com.ilyakrn.foodies.domain.repositories.TagRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//репозиторий тегов
class TagRepositoryImpl : TagRepository {
    companion object {
        var list: ArrayList<Tag>? = null
    }
    override fun getTagList(listener: (List<Tag>) -> Unit) {
        list?.let {
            listener(it)
            return
        }
        val call = RetrofitClient.getClient().create(RetorfitTagService::class.java).getTagList()
        call.enqueue(object : Callback<List<TagJsonModel>> {
            override fun onResponse(call: Call<List<TagJsonModel>>, response: Response<List<TagJsonModel>>) {
                if(response.isSuccessful && response.body() != null){
                    val res = ArrayList<Tag>()
                    response.body()!!.forEach {
                        res.add(Tag(
                            it.id ?: -1,
                            it.name ?: ""))
                    }
                    list = res
                    listener(res)
                }else{
                    listener(ArrayList())
                }
            }

            override fun onFailure(call: Call<List<TagJsonModel>>, t: Throwable) {
                listener(ArrayList())
            }
        })
    }
}