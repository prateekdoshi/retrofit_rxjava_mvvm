package com.prateek.android.retrofit_rxjava_mvvm.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetroHTTP {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        val INSTANCE: RetroHTTP by lazy {
            RetroHTTP()

        }
    }

    private val retro: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun <T> getService(apiClass: Class<T>): T {
        return retro.create(apiClass)
    }
}