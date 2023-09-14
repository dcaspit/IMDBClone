package com.example.imdbclone.fragments.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdbclone.R
import com.example.imdbclone.data.viewModels.DatabaseViewModel
import com.example.imdbclone.databinding.FragmentFavoritesBinding
import com.example.imdbclone.fragments.home.adapters.HomeTopRatedRecyclerAdapter

class FavoritesFragment : Fragment() {

    private val mDatabaseViewModel: DatabaseViewModel by viewModels()

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val movieTopRatedAdapter: HomeTopRatedRecyclerAdapter by lazy { HomeTopRatedRecyclerAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(layoutInflater, container, false)

        // Observe LiveData
        mDatabaseViewModel.getAllMovies.observe(viewLifecycleOwner) { data ->

            movieTopRatedAdapter.setData(data)
            binding.rvFavorites.scheduleLayoutAnimation()
        }

        val recyclerView = binding.rvFavorites
        recyclerView.adapter = movieTopRatedAdapter
        val layoutManager = LinearLayoutManager(
            binding.root.context,
            LinearLayoutManager.VERTICAL, false
        )

        recyclerView.layoutManager = layoutManager

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}