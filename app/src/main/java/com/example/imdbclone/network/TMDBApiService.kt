package com.example.imdbclone.network

import android.os.Parcelable
import androidx.room.util.query
import com.example.imdbclone.data.models.JsonMovie
import com.example.imdbclone.data.models.MovieApiResponse
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.parcelize.Parcelize
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


const val POSTER_BASE_URL = "https://www.themoviedb.org/t/p/w220_and_h330_face"
const val COVER_BASE_URL = "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces"
const val BASE_URL = "https://api.themoviedb.org/3/"
const val HEADER = "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYzliMzJhNmM3ZTZjNmRmNmI3OTIzZmFhMzQ0NmU0ZSIsInN1YiI6IjY0ZmY0ZWRkNmEyMjI3MDEzNzJjY2VmMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.OugtrcAP8bqu6_nBVLUy6zgWywnw8x1T-YXEPeSfy7I"

interface TMDBApiService {
    @Headers(HEADER)
    @GET("movie/popular?language=en-US&page=1")
    suspend fun getMoviesPreviews(): MovieApiResponse

    @Headers(HEADER)
    @GET("movie/top_rated?language=en-US&page=1")
    suspend fun getMoviesTopRated(): MovieApiResponse
    @Headers(HEADER)
    @GET("search/movie?include_adult=false&language=en-US&page=1")
    suspend fun getMoviesSearch(@Query("query") searchQuery: String): MovieApiResponse
}

private val retrofitPopularMovies = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


object TMDBApi {
    val tmdbApiService: TMDBApiService by lazy {
        retrofitPopularMovies.create(TMDBApiService::class.java);
    }
}

