package com.example.imdbclone.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdbclone.R
import com.example.imdbclone.databinding.FragmentHomeBinding
import com.example.imdbclone.fragments.home.adapters.HomeHorizontalRecyclerAdapter
import com.example.imdbclone.fragments.home.adapters.HomeTopRatedRecyclerAdapter
import com.example.imdbclone.data.viewModels.MoviesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment(
) : Fragment() {

    private val moviesViewModel: MoviesViewModel by viewModels()

    private val binding get() = _binding!!
    private var _binding: FragmentHomeBinding? = null

    private val moviePreviewAdapter: HomeHorizontalRecyclerAdapter by lazy { HomeHorizontalRecyclerAdapter() }
    private val movieTopRatedAdapter: HomeTopRatedRecyclerAdapter by lazy { HomeTopRatedRecyclerAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        try{
            _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
            binding.lifecycleOwner = viewLifecycleOwner
            binding.moviesViewModel = moviesViewModel

            setupRecyclerviews()

            return binding.root
        } catch (e: Exception){
            e.printStackTrace()
        }
        return null;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addMenuNavigationToFavoritePage(R.id.action_mainFragment_to_favoritesFragment)
        lifecycleScope.launch(Dispatchers.IO) {
            moviesViewModel.fetchMoviesPreviews()
        }
        lifecycleScope.launch(Dispatchers.IO) {
            moviesViewModel.fetchMoviesTopRated()
        }
    }

    private fun addMenuNavigationToFavoritePage(action: Int) {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.fragments_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.menu_favorites) {
                    findNavController().navigate(action)
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupRecyclerviews() {
        val horizontalRecyclerView = binding.horizonalRecyclerView
        horizontalRecyclerView.adapter = moviePreviewAdapter
        val horizonatalLayoutManager = LinearLayoutManager(
            binding.root.context,
            LinearLayoutManager.HORIZONTAL, false
        )

        horizontalRecyclerView.layoutManager = horizonatalLayoutManager

        val recyclerView = binding.topRatedRecyclerView
        recyclerView.adapter = movieTopRatedAdapter
        val layoutManager = LinearLayoutManager(
            binding.root.context,
            LinearLayoutManager.VERTICAL, false
        )

        recyclerView.layoutManager = layoutManager
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}