package com.example.codingchallenge.viewmodel

import com.example.codingchallenge.model.Product
import com.example.codingchallenge.service.ProductsRepository

class ProductsViewModel {
    //TODO - use a LiveData-based solution instead
    //TODO - response error/status handling to UI (to display statuses)
    suspend fun getProducts(): List<Product>? {
        val response = ProductsRepository.getProducts()
        return if ((response?.size ?: 0) > 0) {
            response
        } else null
    }
}