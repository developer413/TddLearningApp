package com.p413.tddlearning.groovy.playlist

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClientInstance {

    private lateinit var retrofit: Retrofit
    private const val BASE_URL = "https://63e32ccfc919fe386c01baef.mockapi.io/"

    val retrofitInstance: Retrofit
        get() {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }
}