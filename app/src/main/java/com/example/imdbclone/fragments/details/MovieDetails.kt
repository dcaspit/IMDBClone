package com.example.imdbclone.fragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.imdbclone.databinding.FragmentMovieDetailsBinding

class MovieDetails : Fragment() {

    private val args by navArgs<MovieDetailsArgs>()

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        try {
            _binding = FragmentMovieDetailsBinding.inflate(layoutInflater, container, false)
            binding.args = args
            val coverImgUri = ("https://image.tmdb.org/t/p/w1000_and_h450_multi_faces" + args.movie.poster_path).toUri().buildUpon().scheme("https").build()
            binding.ivCover.load(coverImgUri)

            val imgUri = ("https://www.themoviedb.org/t/p/w220_and_h330_face" + args.movie.poster_path).toUri().buildUpon().scheme("https").build()
            binding.ivPoster.load(imgUri)

            binding.executePendingBindings()
            return binding.root
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null;
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}