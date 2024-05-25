package com.example.codingchallenge.model

import com.fasterxml.jackson.annotation.JsonProperty

data class NewsResponse(
    @JsonProperty("status") val status: String,
    @JsonProperty("totalResults") val results: Int,
    @JsonProperty("articles") val articles: List<Article>
)