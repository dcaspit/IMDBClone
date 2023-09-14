package com.example.imdbclone.fragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.imdbclone.data.models.MovieData
import com.example.imdbclone.data.viewModels.DatabaseViewModel
import com.example.imdbclone.databinding.FragmentMovieDetailsBinding

class MovieDetails : Fragment() {

    private val mDatabaseViewModel: DatabaseViewModel by viewModels()

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

            binding.btAddFav.setOnClickListener {
                insertMovieToDatabase()
            }

            binding.executePendingBindings()
            return binding.root
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null;
    }

    private fun insertMovieToDatabase() {
        val mTitle = args.movie.title
        val mDes = args.movie.overview
        val imgUrl = args.movie.poster_path

        val movieData = imgUrl?.let {
            MovieData(
                "0",
                mTitle,
                mDes,
                it
            )
        }
        if (movieData != null) {
            mDatabaseViewModel.insertData(movieData)
            Toast.makeText(requireContext(), "Added!", Toast.LENGTH_LONG).show()
            binding.btAddFav.text = "Already Added to Fav"
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}