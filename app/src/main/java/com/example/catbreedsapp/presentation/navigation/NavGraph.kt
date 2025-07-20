package com.example.catbreedsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.catbreedsapp.presentation.breedlist.BreedListScreen
import com.example.catbreedsapp.presentation.breeddetail.BreedDetailScreen
import com.example.catbreedsapp.data.model.Breed
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.catbreedsapp.presentation.breedlist.BreedListViewModel
import com.example.catbreedsapp.presentation.favourites.FavouritesScreen
import com.google.gson.Gson

@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: BreedListViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.BreedList.route,
        modifier = modifier
    ) {
        composable(route = Screen.BreedList.route) {
            BreedListScreen(
                viewModel = viewModel,
                onBreedClick = { breed ->
                    val breedJson = Gson().toJson(breed)
                    navController.navigate("${Screen.BreedDetail.route}/$breedJson")
                }
            )
        }

        composable(
            route = "${Screen.BreedDetail.route}/{breed}",
            arguments = listOf(navArgument("breed") { type = NavType.StringType })
        ) { backStackEntry ->
            val breedJson = backStackEntry.arguments?.getString("breed")
            val breed = Gson().fromJson(breedJson, Breed::class.java)

            BreedDetailScreen(
                breed = breed,
                isFavorite = viewModel.isFavouriteComposable(breed.id),
                onToggleFavorite = { viewModel.toggleFavourite(it) }
            )
        }
        composable(route = Screen.Favourites.route) {
            val favourites by viewModel.favourites.collectAsState()
            FavouritesScreen(favourites = favourites)
        }
    }
}

