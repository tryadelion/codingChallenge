package com.example.codingchallenge.service

import com.example.codingchallenge.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("apiKey") apikey: String,
        @Query("country") country: String = "",
        @Query("language") language: String = "",
        @Query("category") category: String = "",): Response<NewsResponse>
}