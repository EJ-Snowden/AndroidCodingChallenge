package com.example.android_codingchallenge.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.android_codingchallenge.viewmodel.ImageSearchViewModel
import com.example.android_codingchallenge.presentation.components.ImageItem

@Composable
fun SearchScreen(viewModel: ImageSearchViewModel = hiltViewModel(), navController: NavController) {
    var query by rememberSaveable { mutableStateOf("") }
    val images by viewModel.images.collectAsState()
    val listState = rememberLazyListState()
    var lastQuery by rememberSaveable { mutableStateOf("") }
    var shouldScrollToTop by rememberSaveable { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search Images") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (query.isNotBlank() && query != lastQuery) {
                    lastQuery = query
                    shouldScrollToTop = true
                    viewModel.searchImages(query)
                }
            }
        ) {
            Text("Search")
        }
        Spacer(modifier = Modifier.height(16.dp))

        LaunchedEffect(images) {
            if (shouldScrollToTop) {
                listState.scrollToItem(0)
                shouldScrollToTop = false
            }
        }

        LazyColumn(state = listState) {
            items(images) { image ->
                ImageItem(image) { selectedImage ->
                    navController.navigate("detail/${selectedImage.id}")
                }
            }
        }
    }
}
