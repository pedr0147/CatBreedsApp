package com.example.catbreedsapp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(val url: String)

@JsonClass(generateAdapter = true)
data class Breed(
    val id: String,
    val name: String,
    val origin: String?,
    val temperament: String?,
    val description: String?,
    val image: Image?,
    val life_span: String?
)
