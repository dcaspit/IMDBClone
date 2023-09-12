package com.example.imdbclone

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbclone.fragments.home.adapter.MoviePreviewAdapter
import com.example.imdbclone.fragments.main.MainFragmentDirections
import com.example.imdbclone.models.MovieData

class BindingAdapters {
    companion object {

        @JvmStatic
        @BindingAdapter("android:sendDataToDetailsFragment")
        fun sendMovieData(
            constraintLayout: ConstraintLayout,
            movieData: MovieData
        ){
            constraintLayout.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToMovieDetails(movieData)
                constraintLayout.findNavController().navigate(action)
            }
        }


        @JvmStatic
        @BindingAdapter("populateMoviesPreviewRecycler")
        fun populateMoviesPreviewRecycler(
            recyclerView: RecyclerView,
            moviesData: List<MovieData>?
        ){
            if(recyclerView.adapter != null && recyclerView.adapter is MoviePreviewAdapter){
                val adapter = recyclerView.adapter as MoviePreviewAdapter
                if (moviesData != null) {
                    adapter.setData(moviesData)
                    adapter.notifyDataSetChanged()
                }
            }
            else{
                recyclerView.adapter = MoviePreviewAdapter().apply { setData(listOf()) }
            }
        }

    }
}