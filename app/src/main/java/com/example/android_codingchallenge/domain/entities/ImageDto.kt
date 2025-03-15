package com.example.android_codingchallenge.domain.entities

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("id") val id: Int,
    @SerializedName("user") val username: String,
    @SerializedName("tags") val tags: String,
    @SerializedName("largeImageURL") val imageUrl: String,
    @SerializedName("likes") val likes: Int,
    @SerializedName("downloads") val downloads: Int,
    @SerializedName("comments") val comments: Int
)
