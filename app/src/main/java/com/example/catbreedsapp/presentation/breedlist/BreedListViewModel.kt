package com.example.catbreedsapp.presentation.breedlist

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
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
            viewModelScope.launch{
                val isFav = _favourites.value.any { it.breedId == breed.id }
                if (isFav) {
                    repository.removeFavourite(breed.id)
                } else {
                    repository.addFavourite(
                        FavouriteBreed(
                            breedId = breed.id,
                            name = breed.name,
                            imageUrl = breed.image?.url ?: "",
                            lifeSpan = breed.life_span ?: "Unknown"
                        )
                    )
                }
            }
        }
    }

    fun isFavourite(breedId: String): Boolean {
        return _favourites.value.any { it.breedId == breedId }
    }
}