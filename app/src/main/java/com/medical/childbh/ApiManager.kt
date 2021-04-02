package com.medical.childbh

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {
    var gson = GsonBuilder()
            .setLenient()
            .create()
    var retrofit = Retrofit.Builder()
            .baseUrl("https://crm.towarddevelopment.org/api/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
}