package com.example.android_codingchallenge

import com.example.android_codingchallenge.domain.entities.ImageDto
import com.example.android_codingchallenge.domain.usecases.SearchImagesUseCase
import com.example.android_codingchallenge.viewmodel.ImageSearchViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ImageSearchViewModelTest {

    @Mock
    lateinit var searchImagesUseCase: SearchImagesUseCase

    @Test
    fun `searchImages updates images state flow`() = runTest {
        Dispatchers.setMain(StandardTestDispatcher(testScheduler))

        val mockImages = listOf(ImageDto(id = 1, username = "User1", tags = "tags", imageUrl = "url", likes = 100, downloads = 50, comments = 10))

        whenever(searchImagesUseCase("car")).thenReturn(mockImages)

        val viewModel = ImageSearchViewModel(searchImagesUseCase)

        viewModel.searchImages("car")

        advanceUntilIdle()

        assertEquals(mockImages, viewModel.images.first())
    }
}