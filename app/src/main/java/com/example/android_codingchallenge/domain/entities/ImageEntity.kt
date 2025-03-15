package com.example.android_codingchallenge.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val tags: String,
    val imageUrl: String,
    val likes: Int,
    val downloads: Int,
    val comments: Int
)
