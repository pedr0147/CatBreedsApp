package com.example.catbreedsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.catbreedsapp.data.api.APIClient
import com.example.catbreedsapp.data.db.AppDatabase
import com.example.catbreedsapp.data.repository.CatRepository
import com.example.catbreedsapp.presentation.breedlist.BreedListViewModel
import com.example.catbreedsapp.presentation.navigation.AppNavGraph
import com.example.catbreedsapp.ui.theme.CatBreedsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CatBreedsAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppEntryPoint()
                }
            }
        }
    }
}

@Composable
fun AppEntryPoint() {
    val context = LocalContext.current

    val db = remember {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "cat_breeds_db"
        ).fallbackToDestructiveMigration(false)
            .build()
    }

    val repository = remember {
        CatRepository(APIClient.apiService, db.favouriteDAO())
    }

    val viewModel = remember {
        BreedListViewModel(repository)
    }

    val navController = rememberNavController()

    AppNavGraph(
        navController = navController,
        viewModel = viewModel
    )
}
