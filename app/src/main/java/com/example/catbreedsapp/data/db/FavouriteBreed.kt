package com.example.catbreedsapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")

data class FavouriteBreed(
    @PrimaryKey val breedId: String,
    val name: String,
    val imageUrl: String,
    val origin: String?,
    val temperament: String?,
    val description: String?,
    val lifeSpan: String?
)