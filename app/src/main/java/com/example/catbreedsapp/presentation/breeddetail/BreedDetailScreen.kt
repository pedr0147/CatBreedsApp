package com.example.catbreedsapp.presentation.breeddetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.catbreedsapp.R
import com.example.catbreedsapp.data.model.Breed

@Composable
fun BreedDetailScreen(breed: Breed, isFavorite: Boolean, onToggleFavorite: (Breed) -> Unit) {
    var favorite by remember { mutableStateOf(isFavorite) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(breed.imageUrl )
                .crossfade(true)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .build(),
            contentDescription = breed.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = breed.name,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
            IconButton(onClick = {
                favorite = !favorite
                onToggleFavorite(breed)
            }) {
                Icon(
                    painter = painterResource(
                        id = if (favorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_border
                    ),
                    contentDescription = "Favorite"
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = breed.description ?: "No description available.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Temperament: ${breed.temperament ?: "Unknown"}")
        Text(text = "Origin: ${breed.origin ?: "Unknown"}")
        Text(text = "Life span: ${breed.life_span ?: "Unknown"} years")
    }
}
