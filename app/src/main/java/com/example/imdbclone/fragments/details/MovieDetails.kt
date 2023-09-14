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
import com.example.imdbclone.databinding.FragmentMovieDetailsBinding
import com.example.imdbclone.network.GlideLoader

class MovieDetails : Fragment() {

    private val mDatabaseViewModel: DatabaseViewModel by viewModels()

    private val args by navArgs<MovieDetailsArgs>()

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        try {
            _binding = FragmentMovieDetailsBinding.inflate(layoutInflater, container, false)
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
            "https://www.themoviedb.org/t/p/w1000_and_h450_multi_faces" + args.movie.poster_path,
            binding.ivCover
        )
        glideLoader.loadRoundedImageWithCaching(
            "https://www.themoviedb.org/t/p/w220_and_h330_face" + args.movie.poster_path,
            binding.ivPoster
        )
    }

    private fun handleFavoriteButton() {
        binding.btAddFav.setOnClickListener {
            insertMovieToDatabase()
        }

        mDatabaseViewModel.getMovieByTitle(args.movie.id)
            .observe(viewLifecycleOwner) { movieExsits ->
                if (movieExsits == true) {
                    binding.btAddFav.text = "Added Already"
                    binding.btAddFav.isEnabled = false
                }
            }
    }

    private fun insertMovieToDatabase() {
        val imgUrl = args.movie.poster_path
        val mTitle = args.movie.title
        val mDes = args.movie.overview
        val movieId = args.movie.id

        val movieData = MovieData(
            movieId, mTitle, mDes, imgUrl ?: ""
        )

        mDatabaseViewModel.insertData(movieData)
        Toast.makeText(requireContext(), "Added!", Toast.LENGTH_LONG).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}