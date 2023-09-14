package com.example.imdbclone.fragments.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbclone.data.models.MovieData
import com.example.imdbclone.databinding.MoviePreviewCardBinding
import com.example.imdbclone.fragments.main.MainFragmentDirections
import com.example.imdbclone.network.GlideLoader
import com.example.imdbclone.network.POSTER_BASE_URL
import com.example.imdbclone.utils.MovieDataDiffUtil

class HomeHorizontalRecyclerAdapter: RecyclerView.Adapter<HomeHorizontalRecyclerAdapter.MoviePreviewViewHolder>() {

    private var movieList = emptyList<MovieData>()

    class MoviePreviewViewHolder(
        private val binding: MoviePreviewCardBinding
    ):  RecyclerView.ViewHolder(binding.root){

        private val glideLoader = GlideLoader(binding.root.context)

        fun bind(movieData: MovieData){
            binding.movieData = movieData
            binding.rowBackground.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToMovieDetails(movieData)
                it.findNavController().navigate(action)
            }

            glideLoader.loadRoundedImageWithCaching(POSTER_BASE_URL + movieData.imgUrl, binding.movieImage)

            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): MoviePreviewViewHolder {
                val binding = MoviePreviewCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MoviePreviewViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MoviePreviewViewHolder.from(parent)

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MoviePreviewViewHolder, position: Int) {
        val currentItem = movieList[position]
        holder.bind(currentItem)
    }

    fun setData(moviesData: List<MovieData>){
        val jsonMovieDiffUtil = MovieDataDiffUtil(movieList, moviesData)
        val movieDiffResult = DiffUtil.calculateDiff(jsonMovieDiffUtil)
        this.movieList = moviesData
        movieDiffResult.dispatchUpdatesTo(this)
    }
}