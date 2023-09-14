package com.example.imdbclone.data.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.imdbclone.data.MoviesDatabase
import com.example.imdbclone.data.models.MovieData
import com.example.imdbclone.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseViewModel(application: Application): AndroidViewModel(application) {

    private val movieDao = MoviesDatabase.getDataabase(application).movieDao()
    private val repository: MovieRepository

    private val getAllMovies: LiveData<List<MovieData>>

    init {
        repository = MovieRepository(movieDao)
        getAllMovies = repository.getAllMovies
    }

    fun insertData(movieData: MovieData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMovie(movieData)
        }
    }
}