package com.example.imdbclone

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbclone.fragments.home.HomeFragmentDirections
import com.example.imdbclone.fragments.home.adapter.HomeFragmentHorizontalRecyclerAdapter
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
            MovieApiResponse: MovieApiResponse?
        ) {
            try {
                if(MovieApiResponse == null) return
                if (recyclerView.adapter != null && recyclerView.adapter is HomeFragmentHorizontalRecyclerAdapter) {
                    val adapter = recyclerView.adapter as HomeFragmentHorizontalRecyclerAdapter
                    adapter.setData(MovieApiResponse.results)
                    adapter.notifyItemInserted(0)
                } else {
                    recyclerView.adapter = HomeFragmentHorizontalRecyclerAdapter().apply { setData(listOf()) }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}