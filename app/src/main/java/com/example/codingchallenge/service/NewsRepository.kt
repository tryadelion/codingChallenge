package com.example.codingchallenge.service

import com.example.codingchallenge.BuildConfig
import com.example.codingchallenge.model.NewsResponse

object NewsRepository {
    private const val SERVICE_URL = "https://newsapi.org/v2/"
    private const val SERVICE_COUNTRY = "us"
    private const val SERVICE_KEY = BuildConfig.newsApiKey

    private val client = NetworkClient(SERVICE_URL)
    private val newsAPI = client.getClient().create(NewsAPI::class.java)

    suspend fun getHeadlines(): NewsResponse? {
        val response = newsAPI
            .getHeadlines(
                apikey = SERVICE_KEY,
                country = SERVICE_COUNTRY)
        val successful = response.isSuccessful
        val httpStatusCode = response.code()
        val httpStatusMessage = response.message()
        println("$successful - $httpStatusCode - $httpStatusMessage")
        return response.body()
    }
}