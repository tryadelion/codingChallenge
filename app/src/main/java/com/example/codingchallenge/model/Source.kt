package com.example.codingchallenge.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Source(
    @JsonProperty("id") val id: String?,
    @JsonProperty("name") val name: String
)