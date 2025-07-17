package com.example.catbreedsapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")

data class FavouriteBreed(
    @PrimaryKey val breedId: String,
    val name: String,
    val imageUrl: String,
    val lifeSpan: String?
)