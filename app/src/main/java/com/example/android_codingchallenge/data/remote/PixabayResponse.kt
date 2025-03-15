package com.example.android_codingchallenge.data.remote

import com.example.android_codingchallenge.domain.entities.ImageDto
import com.google.gson.annotations.SerializedName

data class PixabayResponse(
    @SerializedName("hits") val hits: List<ImageDto>
)