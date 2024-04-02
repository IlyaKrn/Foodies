package com.ilyakrn.foodies.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//синглтон клиента Retrofit
object RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}