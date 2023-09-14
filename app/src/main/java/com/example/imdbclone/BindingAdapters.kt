package com.example.imdbclone

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbclone.fragments.home.adapters.HomeHorizontalRecyclerAdapter
import com.example.imdbclone.fragments.home.adapters.HomeTopRatedRecyclerAdapter
import com.example.imdbclone.data.models.MovieData
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

        @JvmStatic
        @BindingAdapter("populateMoviesTopRatedRecycler")
        fun populateMoviesTopRatedRecycler(
            recyclerView: RecyclerView,
            movieApiResponse: MovieApiResponse?
        ) {
            try {
                if (movieApiResponse == null) return
                if (recyclerView.adapter == null || recyclerView.adapter !is HomeTopRatedRecyclerAdapter) {
                    recyclerView.adapter = HomeTopRatedRecyclerAdapter().apply { setData(listOf()) }
                }

                val adapter = recyclerView.adapter as HomeTopRatedRecyclerAdapter
                adapter.setData(movieApiResponse.results)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }



        @JvmStatic
        @BindingAdapter("populateMoviesSearchRecycler")
        fun populateMoviesSearchRecycler(
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

        @JvmStatic
        @BindingAdapter("android:numberText")
        fun setNumberText(textView: TextView, number: Double?){
            if(number == null) return
            textView.text = number.toString()
        }

    }
}