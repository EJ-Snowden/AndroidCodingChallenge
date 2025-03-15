package com.example.android_codingchallenge

import com.example.android_codingchallenge.data.repository.ImageRepository
import com.example.android_codingchallenge.domain.entities.ImageDto
import com.example.android_codingchallenge.domain.usecases.SearchImagesUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SearchImagesUseCaseTest {

    @Mock
    lateinit var repository: ImageRepository

    @Test
    fun `invoke fetches images from repository`() = runTest {
        val query = "cat"
        val mockImages = listOf(ImageDto(id = 1, username = "User1", tags = "tags", imageUrl = "url", likes = 100, downloads = 50, comments = 10))

        whenever(repository.searchImages(query)).thenReturn(mockImages)

        val useCase = SearchImagesUseCase(repository)

        val result = useCase.invoke(query)

        assertEquals(mockImages, result)
    }
}
