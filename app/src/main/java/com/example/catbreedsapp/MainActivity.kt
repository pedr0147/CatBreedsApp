package com.example.catbreedsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.room.Room
import com.example.catbreedsapp.data.api.APIClient
import com.example.catbreedsapp.data.db.AppDatabase
import com.example.catbreedsapp.data.repository.CatRepository
import com.example.catbreedsapp.presentation.breedlist.BreedListScreen
import com.example.catbreedsapp.presentation.breedlist.BreedListViewModel
import com.example.catbreedsapp.ui.theme.CatBreedsAppTheme

class MainActivity : ComponentActivity(){
    override  fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CatBreedsAppTheme {
                AppEntryPoint()
            }
        }
    }
}

@Composable
fun AppEntryPoint() {
    val context = androidx.compose.ui.platform.LocalContext.current
    val db = remember {
        Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "cat_breeds_db"
            ).fallbackToDestructiveMigration(false).build()
    }
    val repository = remember { CatRepository(APIClient.apiService, db.favouriteDAO()) }
    val viewModel = remember { BreedListViewModel(repository) }

    /*Scaffold { padding ->
        BreedListScreen(
            viewModel = viewModel,
            onBreedClick = {
                // Handle breed click, e.g., navigate to details screen
            }
        )
    }*/
}