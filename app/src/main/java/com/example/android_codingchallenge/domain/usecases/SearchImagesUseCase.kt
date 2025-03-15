package com.example.android_codingchallenge.domain.usecases

import com.example.android_codingchallenge.domain.entities.ImageDto
import com.example.android_codingchallenge.data.repository.ImageRepository
import javax.inject.Inject

class SearchImagesUseCase @Inject constructor(
    private val repository: ImageRepository
) {
    suspend operator fun invoke(query: String): List<ImageDto> {
        if (query.isBlank()) return emptyList()

        return repository.searchImages(query)
    }
}
