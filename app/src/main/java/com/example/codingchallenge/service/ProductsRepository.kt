package com.example.codingchallenge.service

import com.example.codingchallenge.model.Product

object ProductsRepository {
    private const val SERVICE_URL = "_"
    private val client = NetworkClient(SERVICE_URL)
    private val newsAPI = client.getClient().create(ProductsAPI::class.java)

    suspend fun getProducts(): List<Product>? {
        val response = newsAPI
            .getProducts()
        val successful = response.isSuccessful
        val httpStatusCode = response.code()
        val httpStatusMessage = response.message()
        println("$successful - $httpStatusCode - $httpStatusMessage")
        return response.body()
    }
}