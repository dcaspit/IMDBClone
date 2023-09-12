package com.example.imdbclone.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdbclone.databinding.FragmentHomePageBinding
import com.example.imdbclone.fragments.home.adapter.MoviePreviewAdapter
import com.example.imdbclone.models.listOfMovies
import com.example.imdbclone.viewModels.MoviesViewModel

class HomePage : Fragment() {

    private val moviesViewModel: MoviesViewModel by viewModels()

    private val binding get() = _binding!!
    private var _binding: FragmentHomePageBinding? = null

    private val moviePreviewAdapter: MoviePreviewAdapter by lazy { MoviePreviewAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomePageBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        binding.moviesViewModel = moviesViewModel

        setupRecyclerview()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //moviesViewModel.fetchMoviesPreviews()
    }

    private fun setupRecyclerview() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = moviePreviewAdapter.apply { setData(listOfMovies) }
        val layoutManager = LinearLayoutManager(
            binding.root.context,
            LinearLayoutManager.HORIZONTAL, false
        )

        recyclerView.layoutManager = layoutManager
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}