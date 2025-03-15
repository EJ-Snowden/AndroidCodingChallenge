package com.example.android_codingchallenge.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_codingchallenge.domain.entities.ImageEntity


@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<ImageEntity>): List<Long>?

    @Query("SELECT * FROM images WHERE tags LIKE '%' || :query || '%'")
    suspend fun getImagesByQuery(query: String): List<ImageEntity>
}

