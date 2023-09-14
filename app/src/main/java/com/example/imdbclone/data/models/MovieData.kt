package com.example.imdbclone.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "movies_table", primaryKeys = ["id"])
@Parcelize
data class MovieData(
    @ColumnInfo(name = "id", index = true) var id: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "img_url") var imgUrl: String
): Parcelable