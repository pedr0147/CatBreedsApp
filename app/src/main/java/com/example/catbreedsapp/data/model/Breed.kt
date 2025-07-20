package com.example.catbreedsapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Breed(
    val id: String,
    val name: String,
    val origin: String?,
    val temperament: String?,
    val description: String?,
    @Json(name = "reference_image_id") val referenceImageId: String?,
    val life_span: String?
) {
    val imageUrl: String?
        get() = referenceImageId?.let { "https://cdn2.thecatapi.com/images/$it.jpg" }
}
