package com.example.imdbclone.data.viewModels

import android.app.Application
import android.app.BroadcastOptions
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imdbclone.data.MoviesDatabase
import com.example.imdbclone.data.models.MovieData
import com.example.imdbclone.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DatabaseViewModel(application: Application) : AndroidViewModel(application) {

    private val movieDao = MoviesDatabase.getDataabase(application).movieDao()
    private val repository: MovieRepository = MovieRepository(movieDao)

    private val getAllMovies: LiveData<List<MovieData>> = repository.getAllMovies

    private val _favMovie = MutableLiveData<Boolean>()
    val favMovie: LiveData<Boolean>
        get() = _favMovie

    fun insertData(movieData: MovieData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMovie(movieData)
        }
    }

    fun getMovieByTitle(id: Int): LiveData<Boolean> {
        return repository.getMovieById(id)
    }
}