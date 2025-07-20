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
    val reference_image_id: String?, // novo campo
    val life_span: String?
) {
    val imageUrl: String?
        get() = reference_image_id?.let { "https://cdn2.thecatapi.com/images/$it.jpg" }
}
