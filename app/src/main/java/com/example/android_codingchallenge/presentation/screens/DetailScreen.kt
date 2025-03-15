package com.example.android_codingchallenge.presentation.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import coil.compose.AsyncImage
import com.example.android_codingchallenge.domain.entities.ImageDto

@Composable
fun DetailScreen(image: ImageDto?, onBack: () -> Unit) {
    if (image == null) {
        Text("Error: Image not found")
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Button(onClick = onBack) {
                Text("Back")
            }

            Spacer(modifier = Modifier.height(8.dp))

            AsyncImage(
                model = image.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

            Text(text = "User: ${image.username}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Tags: ${image.tags}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Likes: ${image.likes}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Downloads: ${image.downloads}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Comments: ${image.comments}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
