package com.example.codingchallenge.viewmodel

import com.example.codingchallenge.model.Article
import com.example.codingchallenge.service.NewsRepository

class NewsViewModel {
    //TODO - use a LiveData-based solution instead
    //TODO - response error/status handling to UI (to display statuses)
    suspend fun getHeadlines(withKeyWord: String? = null): List<Article>? {
        val response = NewsRepository.getHeadlines(withKeyWord)
        return if ((response?.results ?: 0) > 0) {
            response?.articles
        } else null
    }
}