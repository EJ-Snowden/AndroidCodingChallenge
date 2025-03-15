package com.example.android_codingchallenge.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.android_codingchallenge.presentation.screens.DetailScreen
import com.example.android_codingchallenge.presentation.screens.SearchScreen
import com.example.android_codingchallenge.viewmodel.ImageSearchViewModel

@Composable
fun AppNavGraph(navController: NavHostController, viewModel: ImageSearchViewModel = hiltViewModel()) {
    NavHost(navController = navController, startDestination = "search") {
        composable("search") {
            SearchScreen(navController = navController, viewModel = viewModel)
        }
        composable("detail/{imageId}") { backStackEntry ->
            val imageId = backStackEntry.arguments?.getString("imageId")?.toIntOrNull()

            val images by viewModel.images.collectAsState()
            val image = images.firstOrNull { it.id == imageId }

            if (image != null) {
                DetailScreen(image = image, onBack = { navController.popBackStack() })
            } else {
                Text("Error: Image not found")
            }
        }
    }
}
