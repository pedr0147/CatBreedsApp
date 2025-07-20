package com.example.catbreedsapp.presentation.breedlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.catbreedsapp.data.model.Breed

@Composable
fun BreedListScreen(
    viewModel: BreedListViewModel,
    onBreedClick: (Breed) -> Unit
) {
    val breeds by viewModel.filteredBreeds.collectAsState()
    val search by viewModel.searchQuery.collectAsState()
    Text(
        text = "Cat Breed App",
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = search,
            onValueChange = { viewModel.updateSearchQuery(it) },
            label = { Text("Search Breed") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .padding(0.dp, 40.dp, 0.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(breeds) { breed ->
                BreedItem(
                    breed = breed,
                    isFavourite = viewModel.isFavouriteComposable(breed.id),
                    onToggleFavourite = { viewModel.toggleFavourite(breed) },
                    onClick = { onBreedClick(breed) }
                )
            }
        }
    }
}

@Composable
fun BreedItem(
    breed: Breed,
    isFavourite: Boolean,
    onToggleFavourite: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painter = rememberAsyncImagePainter(breed.imageUrl)
            Image(
                painter = painter,
                contentDescription = "Cat Image",
                modifier = Modifier
                    .size(72.dp)
                    .padding(end = 12.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(text = breed.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "Origin: ${breed.origin ?: "?"}", style = MaterialTheme.typography.bodySmall)
            }

            IconButton(onClick = onToggleFavourite) {
                Icon(
                    imageVector = if (isFavourite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavourite) Color.Red else Color.Gray
                )
            }
        }
    }
}
