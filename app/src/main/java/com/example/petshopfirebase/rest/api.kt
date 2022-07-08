package com.example.petshopfirebase.rest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface api {
    @GET("sendMessage")
    fun order(@Query("chat_id") id : String,@Query("text") message : String): Call<String>
}