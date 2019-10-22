package com.example.image.modal

import com.google.gson.annotations.SerializedName

data class Photos(
    @field:SerializedName("page")
    val page: Int,
    @field:SerializedName("pages")
    val pages: Int,
    @field:SerializedName("perpage")
    val perpage: Int,
    @field:SerializedName("photo")
    val photo: List<Photo>,
    @field:SerializedName("total")
    val total: String
)