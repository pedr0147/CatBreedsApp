package com.example.catbreedsapp.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(breed: FavouriteBreed)

    @Query("DELETE FROM favourites WHERE breedId = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM favourites")
    fun getAll(): Flow<List<FavouriteBreed>>
}