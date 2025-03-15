package com.example.android_codingchallenge.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {
    @GET("/api/")
    suspend fun searchImages(
        @Query("key") apiKey: String,
        @Query("q") query: String
    ): PixabayResponse
}