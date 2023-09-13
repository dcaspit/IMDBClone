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

class MoviesViewModel(application: Application): AndroidViewModel(application) {


    private var _moviesPreview = MutableLiveData<MovieApiResponse>()
    val moviesPreview : LiveData<MovieApiResponse>
        get() = _moviesPreview

    private var _moviesTopRated =  MutableLiveData<MovieApiResponse>()
    val moviesTopRated : LiveData<MovieApiResponse>
        get() = _moviesTopRated


    fun fetchMoviesPreviews() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.Default){
                    if(_moviesPreview.value == null){
                        val call = TMDBApi.tmdbApiService.getMoviesPreviews()
                        _moviesPreview.postValue(call)
                    }
                }
            }catch (e: Exception){
                Log.d("ERROR", e.stackTraceToString())
            }
        }
    }

    fun fetchMoviesTopRated() {
        viewModelScope.launch {
            try{
                if(_moviesTopRated.value != null) return@launch
                val call = TMDBApi.tmdbApiService.getMoviesTopRated()
                _moviesTopRated.postValue(call)
            } catch (e: Exception){
                Log.d("ERROR", e.stackTraceToString())
            }
        }
    }

    fun resetObservables(){
        _moviesPreview.value = MovieApiResponse(0, listOf(), 0, 0)
    }

}