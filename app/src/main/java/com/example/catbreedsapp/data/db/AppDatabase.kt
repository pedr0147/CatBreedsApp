package com.example.catbreedsapp.data.db


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavouriteBreed::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteDAO(): FavouriteDAO
}