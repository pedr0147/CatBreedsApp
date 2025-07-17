package com.example.catbreedsapp.data.repository

import com.example.catbreedsapp.data.api.CatAPIService
import com.example.catbreedsapp.data.db.FavouriteBreed
import com.example.catbreedsapp.data.db.FavouriteDAO
import com.example.catbreedsapp.data.model.Breed
import kotlinx.coroutines.flow.Flow

class CatRepository (
    private val api: CatAPIService,
    private val dao: FavouriteDAO
) {
    suspend fun getBreeds(): List<Breed> = api.getBreeds()

    suspend fun addFavourite(breed: FavouriteBreed) = dao.insert(breed)

    suspend fun removeFavourite(id: String) = dao.deleteById(id)

    fun getFavourites(): Flow<List<FavouriteBreed>> = dao.getAll()
}