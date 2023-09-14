package com.example.imdbclone.fragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.imdbclone.data.models.MovieData
import com.example.imdbclone.data.viewModels.DatabaseViewModel
import com.example.imdbclone.databinding.FragmentDetailsBinding
import com.example.imdbclone.network.COVER_BASE_URL
import com.example.imdbclone.network.GlideLoader
import com.example.imdbclone.network.POSTER_BASE_URL

class MovieDetails : Fragment() {

    private val mDatabaseViewModel: DatabaseViewModel by viewModels()

    private val args by navArgs<MovieDetailsArgs>()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        try {
            _binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
            binding.args = args

            loadPageImages()
            handleFavoriteButton()

            binding.executePendingBindings()
            return binding.root
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null;
    }

    private fun loadPageImages() {
        val glideLoader = GlideLoader(binding.root.context)
        glideLoader.loadImageWithCaching(
            COVER_BASE_URL + args.movieData.imgUrl,
            binding.ivCover
        )
        glideLoader.loadRoundedImageWithCaching(
            POSTER_BASE_URL + args.movieData.imgUrl,
            binding.ivPoster
        )
    }

    private fun handleFavoriteButton() {
        binding.btAddFav.setOnClickListener {
            insertMovieToDatabase()
        }

        mDatabaseViewModel.getMovieByTitle(args.movieData.movieId)
            .observe(viewLifecycleOwner) { movieExsits ->
                if (movieExsits == true) {
                    binding.btAddFav.text = "Remove"
                    binding.btAddFav.isEnabled = false
                }
            }
    }

    private fun insertMovieToDatabase() {
        val imgUrl = args.movieData.imgUrl
        val mTitle = args.movieData.title
        val mDes = args.movieData.description
        val movieId = args.movieData.movieId

        val movieData = MovieData(
            movieId, mTitle, mDes, imgUrl ?: "", args.movieData.popularity, args.movieData.release_date
        )

        mDatabaseViewModel.insertData(movieData)
        Toast.makeText(requireContext(), "Added!", Toast.LENGTH_LONG).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}