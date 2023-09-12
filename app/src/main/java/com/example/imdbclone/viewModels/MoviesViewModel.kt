package com.example.imdbclone.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imdbclone.models.MovieData
import com.example.imdbclone.models.listOfMovies
import com.example.imdbclone.network.MovieApiResponse
import com.example.imdbclone.network.TMDBApi
import com.example.imdbclone.network.TMDBApiService
import com.example.imdbclone.utils.SingleLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel(application: Application): AndroidViewModel(application) {


    private var _moviesPreview = MutableLiveData<MovieApiResponse>()
    val moviesPreview : LiveData<MovieApiResponse>
        get() = _moviesPreview


    fun fetchMoviesPreviews() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.Default){
                    delay(200)
                    val call = TMDBApi.tmdbApiService.getMovies()
                    _moviesPreview.postValue(call)
//                    call.enqueue(object : Callback<MovieApiResponse> {
//                        override fun onResponse(
//                            call: Call<MovieApiResponse>,
//                            response: Response<MovieApiResponse>
//                        ) {
//                            if(response.isSuccessful){
//                                val movieApiResponse = response.body()
//                                if(movieApiResponse != null){
//                                    _moviesPreview.postValue(movieApiResponse!!)
//                                }
//                            }
//                        }
//
//                        override fun onFailure(call: Call<MovieApiResponse>, t: Throwable) {
//                            t.message?.let { Log.d("BAD", it) };
//                        }
//
//                    })

                }
            }catch (e: Exception){
                Log.d("ERROR", e.stackTraceToString())
            }
        }
    }

    fun resetObservables(){
        _moviesPreview.value = MovieApiResponse(0, listOf(), 0, 0)
    }

}