package com.example.imdbclone.data.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imdbclone.data.models.MovieData
import com.example.imdbclone.data.viewModels.MoviesViewModel.Companion.toMovieList
import com.example.imdbclone.network.TMDBApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private var _moviesSearchResults = MutableLiveData<List<MovieData>>()
    val moviesSearchResults: LiveData<List<MovieData>>
        get() = _moviesSearchResults

    fun fetchMoviesSearchResults(query: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.Default) {
                    val response = TMDBApi.tmdbApiService.getMoviesSearch(query)
                    _moviesSearchResults.postValue(response.toMovieList())
                }
            } catch (e: Exception) {
                Log.d("ERROR", e.stackTraceToString())
            }
        }
    }
}