package com.example.imdbclone.fragments.movieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.imdbclone.R
import com.example.imdbclone.databinding.FragmentMovieDetailsBinding

class MovieDetails : Fragment() {

    private val args by navArgs<MovieDetailsArgs>()


    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(layoutInflater, container, false)

        binding.args = args
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}