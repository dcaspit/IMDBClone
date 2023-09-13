package com.example.imdbclone

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbclone.fragments.home.adapter.HomeHorizontalRecyclerAdapter
import com.example.imdbclone.models.MovieData
import com.example.imdbclone.network.MovieApiResponse

class BindingAdapters {
    companion object {

        @JvmStatic
        @BindingAdapter("android:sendDataToDetailsFragment")
        fun sendMovieData(
            constraintLayout: ConstraintLayout,
            movieData: MovieData
        ) {
            constraintLayout.setOnClickListener {
//                val action = HomeFragmentDirections.actionHomePageToMovieDetails(movieData)
//                constraintLayout.findNavController().navigate(action)
            }
        }


        @JvmStatic
        @BindingAdapter("populateMoviesPreviewRecycler")
        fun populateMoviesPreviewRecycler(
            recyclerView: RecyclerView,
            movieApiResponse: MovieApiResponse?
        ) {
            try {
                if (movieApiResponse == null) return
                if (recyclerView.adapter == null || recyclerView.adapter !is HomeHorizontalRecyclerAdapter) {
                    recyclerView.adapter = HomeHorizontalRecyclerAdapter().apply { setData(listOf()) }
                }

                val adapter = recyclerView.adapter as HomeHorizontalRecyclerAdapter
                adapter.setData(movieApiResponse.results)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}