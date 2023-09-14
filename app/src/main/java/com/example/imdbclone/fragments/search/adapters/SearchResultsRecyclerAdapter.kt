package com.example.imdbclone.fragments.search.adapters

import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.imdbclone.databinding.MoviePreviewCardBinding
import com.example.imdbclone.fragments.home.adapters.HomeHorizontalRecyclerAdapter
import com.example.imdbclone.fragments.home.adapters.MovieDiffUtil
import com.example.imdbclone.fragments.main.MainFragmentDirections
import com.example.imdbclone.network.Movie

class SearchResultsRecyclerAdapter: RecyclerView.Adapter<HomeHorizontalRecyclerAdapter.MoviePreviewViewHolder>() {

    var searchList = emptyList<Movie>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) =  HomeHorizontalRecyclerAdapter.MoviePreviewViewHolder.from(parent)

    override fun getItemCount(): Int = searchList.size

    override fun onBindViewHolder(
        holder: HomeHorizontalRecyclerAdapter.MoviePreviewViewHolder,
        position: Int
    ) {
        val currentItem = searchList[position]
        holder.bind(currentItem)
    }

    fun setData(moviesData: List<Movie>){
        val movieDiffUtil = MovieDiffUtil(searchList, moviesData)
        val movieDiffResult = DiffUtil.calculateDiff(movieDiffUtil)
        this.searchList = moviesData
        movieDiffResult.dispatchUpdatesTo(this)
    }

}