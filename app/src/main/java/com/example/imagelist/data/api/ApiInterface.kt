package com.example.imagelist.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/shibes")
    suspend fun getImage(@Query("count") count: Int): List<String>

}