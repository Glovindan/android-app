package com.example.sreda.data

import com.google.gson.annotations.SerializedName

/**
 * @author s.timirov
 */
class Response(
    @SerializedName("data")
    val users: List<User>
)
