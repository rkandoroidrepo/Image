package com.example.image.modal

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @field:SerializedName("photos")
    val photos: Photos
)