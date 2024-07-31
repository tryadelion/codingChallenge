package com.example.codingchallenge.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.util.Date

@JsonIgnoreProperties(ignoreUnknown = true)
data class Product(
    @JsonProperty("name") val name: String,
    @JsonProperty("tagline") val tagline: String?,
    @JsonProperty("rating") val rating: Double,
    @JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "M-dd-yyyy"
    )
    @JsonProperty("date") val date: Date?
): Serializable