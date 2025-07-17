package com.example.catbreedsapp.data.model

data class Image(val url: String)

data class Breed(
    val id: String,
    val name: String,
    val origin: String?,
    val temperament: String?,
    val description: String?,
    val image: Image?,
    val life_span: String?
)
