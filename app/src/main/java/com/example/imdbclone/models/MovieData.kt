package com.example.imdbclone.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieData(
    var id: Int,
    var title: String,
    var description: String,
    var imgUrl: String
): Parcelable


val listOfMovies = listOf<MovieData>(
    MovieData(1, "BladeRunner2099", "Fun Movie", "url.."),
    MovieData(2, "Pulp Fiction", "Fun Movie", "url.."),
    MovieData(3, "Toy Story", "Fun Movie", "url.."),
    MovieData(4, "Harry Potter", "Fun Movie", "url.."),
)