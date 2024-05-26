package com.example.codingchallenge.viewmodel

import com.example.codingchallenge.model.Article
import com.example.codingchallenge.service.NewsRepository

class NewsViewModel {

    suspend fun getHeadlines(): List<Article>? {
        val response = NewsRepository.getHeadlines()
        return if ((response?.results ?: 0) > 0) {
            response?.articles
        } else null
    }
}