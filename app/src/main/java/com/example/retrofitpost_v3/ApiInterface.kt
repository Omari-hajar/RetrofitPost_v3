package com.example.retrofitpost_v3

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


//https://dojo-recipes.herokuapp.com/test/

interface ApiInterface {

    @GET("test/")

    suspend fun getData(): Response<List<Data>>

    @POST("test/")


    //create a response class

    fun postData(@Body data: Data): Call<Data>
}