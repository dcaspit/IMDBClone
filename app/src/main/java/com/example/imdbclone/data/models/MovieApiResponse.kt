package com.example.imdbclone.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieApiResponse(
    val page: Int,
    val results: List<JsonMovie>,
    val total_pages: Int,
    val total_results: Int
)