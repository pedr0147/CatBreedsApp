package com.example.catbreedsapp.data.api

import com.example.catbreedsapp.data.model.Breed
import retrofit2.http.GET

interface CatAPIService {
    @GET("breeds")
    suspend fun getBreeds(): List<Breed>
}