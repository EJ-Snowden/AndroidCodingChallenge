package com.example.android_codingchallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_codingchallenge.domain.entities.ImageDto
import com.example.android_codingchallenge.domain.usecases.SearchImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageSearchViewModel @Inject constructor(
    private val searchImagesUseCase: SearchImagesUseCase
) : ViewModel() {

    private val _images = MutableStateFlow<List<ImageDto>>(emptyList())
    val images = _images.asStateFlow()

    fun searchImages(query: String) {
        viewModelScope.launch {
            _images.value = searchImagesUseCase(query)
        }
    }

    init {
        searchImages("fruits")
    }
}

