package com.example.imdbclone

import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.example.imdbclone.fragments.main.MainFragmentDirections
import com.example.imdbclone.models.MovieData
import com.google.android.material.card.MaterialCardView

class BindingAdapters {
    companion object {

        @JvmStatic
        @BindingAdapter("android:sendDataToDetailsFragment")
        fun sendMovieData(
            materialCardView: MaterialCardView,
            movieData: MovieData
        ){
            materialCardView.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToMovieDetails(movieData)
                materialCardView.findNavController().navigate(action)
            }
        }

    }
}