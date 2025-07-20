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

@Composable
fun FavouritesScreen(favourites: List<FavouriteBreed>) {
    Column(modifier = Modifier.fillMaxSize()) {
        if (favourites.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("You don't have any favourites yet")
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(favourites) { fav ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = fav.name, style = MaterialTheme.typography.titleMedium)
                            Text(text = "Life Span: ${fav.lifeSpan} Years")
                        }
                    }
                }
            }
        }
    }
}
