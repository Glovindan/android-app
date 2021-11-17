package com.example.sreda.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author s.timirov
 */
object RetrofitProvider {

    val userService: UserService by lazy { retrofit.create(UserService::class.java) }

    private val retrofit: Retrofit by lazy { createRetrofit() }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
