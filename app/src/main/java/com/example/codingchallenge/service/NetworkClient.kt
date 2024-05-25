package com.example.codingchallenge.service

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class NetworkClient(val serviceUrl: String) {
    fun getClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(serviceUrl)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
}