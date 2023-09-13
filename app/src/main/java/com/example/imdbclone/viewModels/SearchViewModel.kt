package com.example.imdbclone.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imdbclone.network.MovieApiResponse
import com.example.imdbclone.network.TMDBApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(application: Application): AndroidViewModel(application) {

    private var _moviesSearchResults = MutableLiveData<MovieApiResponse>()
    val moviesSearchResults : LiveData<MovieApiResponse>
        get() = _moviesSearchResults

    fun fetchMoviesSearchResults(query: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.Default){
                    if(_moviesSearchResults.value == null){
                        val call = TMDBApi.tmdbApiService.getMoviesSearch(query)
                        _moviesSearchResults.postValue(call)
                    }
                }
            }catch (e: Exception){
                Log.d("ERROR", e.stackTraceToString())
            }
        }
    }
}