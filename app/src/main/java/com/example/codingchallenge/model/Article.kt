package com.example.codingchallenge.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.util.Date

@JsonIgnoreProperties(ignoreUnknown = true)
data class Article(
    @JsonProperty("title") val title: String,
    @JsonProperty("source") val source: Source,
    @JsonProperty("author") val author: String?,
    @JsonProperty("description") val description: String?,
    @JsonProperty("content") val content: String?,
    @JsonProperty("url") val url: String,
    @JsonProperty("urlToImage") val imageUrl: String?,
    @JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    )
    @JsonProperty("publishedAt") val publishedAt: Date?
): Serializable