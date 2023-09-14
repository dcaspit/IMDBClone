package com.example.imdbclone.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imdbclone.databinding.FragmentSearchPageBinding
import com.example.imdbclone.fragments.home.adapters.HomeHorizontalRecyclerAdapter
import com.example.imdbclone.data.viewModels.SearchViewModel

class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()

    private val binding get() = _binding!!
    private var _binding: FragmentSearchPageBinding? = null

    private val moviePreviewAdapter: HomeHorizontalRecyclerAdapter by lazy { HomeHorizontalRecyclerAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchPageBinding.inflate(layoutInflater, container, false)
        // Set up a listener for search query changes
        binding.search.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query == null) return false
                performSearch(query);
                return true;
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })

        binding.lifecycleOwner = viewLifecycleOwner
        binding.searchViewModel = searchViewModel

        setupRecyclerviews()

        return binding.root
    }

    private fun setupRecyclerviews() {
        val recyclerView = binding.searchRecyclerView
        recyclerView.adapter = moviePreviewAdapter
        val layoutManager =  StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        recyclerView.layoutManager = layoutManager
    }

    private fun performSearch(query: String) {
        searchViewModel.fetchMoviesSearchResults(query)
    }
}