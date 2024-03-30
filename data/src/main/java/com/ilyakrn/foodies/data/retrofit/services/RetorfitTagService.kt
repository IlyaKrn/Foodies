package com.ilyakrn.foodies.data.retrofit.services

import com.ilyakrn.foodies.data.retrofit.GET_TAG_LIST
import com.ilyakrn.foodies.data.retrofit.models.TagJsonModel
import com.ilyakrn.foodies.domain.models.core.Tag
import retrofit2.Call
import retrofit2.http.GET

interface RetorfitTagService {
    @GET(GET_TAG_LIST)
    fun getTagList(): Call<List<TagJsonModel>>
}