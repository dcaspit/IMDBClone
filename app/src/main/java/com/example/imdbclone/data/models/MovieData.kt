package com.example.imdbclone.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "movies_table")
@Parcelize
data class MovieData(
    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "img_url") val imgUrl: String,
    @ColumnInfo(name = "popularity") val popularity: String,
    @ColumnInfo(name = "release_date") val release_date: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
): Parcelable