package com.ilyakrn.foodies.data.retrofit.services

import com.ilyakrn.foodies.data.retrofit.GET_CATEGORY_LIST
import com.ilyakrn.foodies.data.retrofit.models.CategoryJsonModel
import com.ilyakrn.foodies.domain.models.core.Category
import retrofit2.Call
import retrofit2.http.GET

interface RetorfitCategoryService {
    @GET(GET_CATEGORY_LIST)
    fun getCategoryList(): Call<List<CategoryJsonModel>>
}