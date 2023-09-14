package com.example.imdbclone.data.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imdbclone.data.models.MovieApiResponse
import com.example.imdbclone.data.models.MovieData
import com.example.imdbclone.network.TMDBApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(application: Application) : AndroidViewModel(application) {


    private var _moviesPreview = MutableLiveData<List<MovieData>>()
    val moviesPreview: LiveData<List<MovieData>>
        get() = _moviesPreview

    private var _moviesTopRated = MutableLiveData<List<MovieData>>()
    val moviesTopRated: LiveData<List<MovieData>>
        get() = _moviesTopRated


    fun fetchMoviesPreviews() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (_moviesPreview.value != null) return@launch
                val response = TMDBApi.tmdbApiService.getMoviesPreviews()
                _moviesPreview.postValue(
                    response.toMovieList()
                )
            } catch (e: Exception) {
                Log.d("ERROR", e.stackTraceToString())
            }
        }
    }

    fun fetchMoviesTopRated() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (_moviesTopRated.value != null) return@launch
                val response = TMDBApi.tmdbApiService.getMoviesTopRated()
                _moviesTopRated.postValue(
                    response.toMovieList()
                )
            } catch (e: Exception) {
                Log.d("ERROR", e.stackTraceToString())
            }
        }
    }

    companion object {
        fun MovieApiResponse.toMovieList() = this.results.map {
            with((it)) {
                MovieData(
                    id, title, overview, poster_path ?: "", popularity.toString(), release_date
                )
            }
        }
    }

    fun resetObservables() {
        _moviesPreview.value = emptyList()
    }

}