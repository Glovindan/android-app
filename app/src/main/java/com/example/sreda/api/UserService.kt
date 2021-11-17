package com.example.sreda.api

import com.example.sreda.data.Response
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author s.timirov
 */
interface UserService {

    @GET("api/users")
    fun getUsers(@Query("page") page: Int): Call<Response>
}
