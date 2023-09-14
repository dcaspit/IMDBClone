package com.example.imdbclone.data.repository

import androidx.lifecycle.LiveData
import com.example.imdbclone.data.MovieDao
import com.example.imdbclone.data.models.MovieData

class MovieRepository(private val movieDao: MovieDao) {

    val getAllMovies: LiveData<List<MovieData>> = movieDao.getAllMovies()

    fun insertMovie(movieData: MovieData) {
        movieDao.insertMovie(movieData)
    }

    fun getMovieById(id: Int) = movieDao.isMovieExists(id)
}