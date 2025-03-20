package com.example.android_codingchallenge.data.repository
import com.example.android_codingchallenge.BuildConfig
import com.example.android_codingchallenge.data.local.ImageDao
import com.example.android_codingchallenge.domain.entities.ImageDto
import com.example.android_codingchallenge.domain.entities.ImageEntity
import com.example.android_codingchallenge.data.remote.PixabayApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepository @Inject constructor(
    private val api: PixabayApi,
    private val dao: ImageDao
) {
    suspend fun searchImages(query: String): List<ImageDto> {
        val localImages = dao.getImagesByQuery(query)
        if (localImages.isNotEmpty()) {
            localImages.map { ImageDto(it.id, it.username, it.tags, it.imageUrl, it.likes, it.downloads, it.comments) }
        }

        val remoteImages = api.searchImages(BuildConfig.PIXABAY_API_KEY, query).hits.map {
            ImageEntity(it.id, it.username, it.tags, it.imageUrl, it.likes, it.downloads, it.comments)
        }


        dao.insertImages(remoteImages)

        return remoteImages.map { ImageDto(it.id, it.username, it.tags, it.imageUrl, it.likes, it.downloads, it.comments) }
    }

}