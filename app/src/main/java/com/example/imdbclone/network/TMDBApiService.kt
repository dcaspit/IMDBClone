package com.example.imdbclone.network

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.parcelize.Parcelize
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Headers


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

interface TMDBApiService {
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYzliMzJhNmM3ZTZjNmRmNmI3OTIzZmFhMzQ0NmU0ZSIsInN1YiI6IjY0ZmY0ZWRkNmEyMjI3MDEzNzJjY2VmMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.OugtrcAP8bqu6_nBVLUy6zgWywnw8x1T-YXEPeSfy7I")
    @GET("movie/popular?language=en-US&page=1")
    suspend fun getMovies(): MovieApiResponse
}
private const val IMAGE_BASE_URL = "https://www.themoviedb.org/t/p/w220_and_h330_face"
private const val BASE_URL = "https://api.themoviedb.org/3/"

private val retrofitPopularMovies = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


object TMDBApi {
    val tmdbApiService: TMDBApiService by lazy {
        retrofitPopularMovies.create(TMDBApiService::class.java);
    }
}

@JsonClass(generateAdapter = true)
data class MovieApiResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

@JsonClass(generateAdapter = true)
@Parcelize
data class Movie(
    val adult: Boolean,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
): Parcelable
