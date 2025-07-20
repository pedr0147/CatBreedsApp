package com.example.catbreedsapp.presentation.navigation

sealed class Screen(val route: String) {
    object BreedList : Screen("breed_list")
    object BreedDetail : Screen("breed_detail")
    object Favourites : Screen("favourites")
}
