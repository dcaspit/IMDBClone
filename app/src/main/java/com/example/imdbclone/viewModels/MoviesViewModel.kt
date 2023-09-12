package com.example.imdbclone.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imdbclone.models.MovieData
import com.example.imdbclone.models.listOfMovies
import com.example.imdbclone.utils.SingleLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(application: Application): AndroidViewModel(application) {


    private var _moviesPreview = SingleLiveData<List<MovieData>>()
    val moviesPreview : LiveData<List<MovieData>>
        get() = _moviesPreview


    fun fetchMoviesPreviews() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.Default){
                    delay(200)
                    _moviesPreview.postValue(listOfMovies)
                }
            }catch (e: Exception){
                Log.d("ERROR", e.stackTraceToString())
            }
        }
    }

    fun resetObservables(){
        _moviesPreview.value = listOf()
    }

}