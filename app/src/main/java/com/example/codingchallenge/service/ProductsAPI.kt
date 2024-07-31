package com.example.codingchallenge.service

import com.example.codingchallenge.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductsAPI {

    @GET("products.v1.json")
    suspend fun getProducts(): Response<List<Product>>
}