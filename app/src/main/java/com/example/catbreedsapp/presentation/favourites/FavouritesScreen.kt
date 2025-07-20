package com.example.catbreedsapp.presentation.favourites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.catbreedsapp.data.db.FavouriteBreed
import com.example.catbreedsapp.data.model.Breed
import com.example.catbreedsapp.presentation.breedlist.BreedItem

@Composable
fun FavouritesScreen(
    favourites: List<FavouriteBreed>,
    onBreedClick: (Breed) -> Unit,
    isFavourite: (String) -> Boolean,
    onToggleFavourite: (Breed) -> Unit
) {
    if (favourites.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("You don't have any favourites yet")
        }
    } else {
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(favourites) { fav ->

                val imageId = fav.imageUrl
                    .substringAfterLast("/")
                    .substringBefore(".")

                val breed = Breed(
                    id = fav.breedId,
                    name = fav.name,
                    origin = fav.origin,
                    temperament = fav.temperament,
                    description = fav.description,
                    referenceImageId = imageId,
                    life_span = fav.lifeSpan
                )

                BreedItem(
                    breed = breed,
                    isFavourite = isFavourite(breed.id),
                    onToggleFavourite = { onToggleFavourite(breed) },
                    onClick = { onBreedClick(breed) }
                )
            }
        }
    }
}
