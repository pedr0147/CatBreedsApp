package com.example.catbreedsapp.presentation.breedlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catbreedsapp.data.db.FavouriteBreed
import com.example.catbreedsapp.data.model.Breed
import com.example.catbreedsapp.data.repository.CatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BreedListViewModel(
    private val repository: CatRepository
): ViewModel(){

    private val _breeds = MutableStateFlow<List<Breed>>(emptyList())
    val breeds: StateFlow<List<Breed>> = _breeds


    private val _favourites = repository.getFavourites().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    val favourites: StateFlow<List<FavouriteBreed>> = _favourites

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    val filteredBreeds = combine(_breeds, _searchQuery) {list, query ->
        if (query.isBlank()) {
            list
        } else {
            list.filter { it.name.contains(query, ignoreCase = true) }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        loadBreeds()
    }

    private fun loadBreeds(){
        viewModelScope.launch{
            try {
                val result = repository.getBreeds()
                _breeds.value = result
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun toggleFavourite(breed: Breed){
        viewModelScope.launch{

                val isFav = _favourites.value.any { it.breedId == breed.id }
                if (isFav) {
                    println("Removing ${breed.name}")
                    repository.removeFavourite(breed.id)
                } else {
                    println("Adding ${breed.name} to favourites")
                    repository.addFavourite(
                        FavouriteBreed(
                            breedId = breed.id,
                            name = breed.name,
                            imageUrl = breed.referenceImageId ?: "",
                            lifeSpan = breed.life_span ?: "Unknown",
                            origin = breed.origin ?: "Unknown",
                            temperament = breed.temperament ?: "Unknown",
                            description = breed.description ?: "No description available"
                        )
                    )
                }

        }
    }

    @Composable
    fun isFavouriteComposable(breedId: String): Boolean {
        val favourites by _favourites.collectAsState()
        return favourites.any { it.breedId == breedId }
    }
    fun isFavourite(id: String): Boolean {
        return _favourites.value.any { it.breedId == id }
    }


}