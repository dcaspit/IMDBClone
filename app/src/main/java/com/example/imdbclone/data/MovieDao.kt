package com.example.imdbclone.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.imdbclone.data.models.MovieData

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies_table ORDER BY id ASC")
    fun getAllMovies(): LiveData<List<MovieData>>

    // When a new item comes into our Database, that is the same
    // as an item that we already have, we will ignore it.
    @Insert
    fun insertMovie(movieData: MovieData)

}
