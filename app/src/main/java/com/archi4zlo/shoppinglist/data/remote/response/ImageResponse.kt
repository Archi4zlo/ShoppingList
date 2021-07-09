package com.archi4zlo.shoppinglist.data.remote.response

data class ImageResponse(
    val hits: List<ImageResult>,
    val total: Int,
    val totalHints: Int
)