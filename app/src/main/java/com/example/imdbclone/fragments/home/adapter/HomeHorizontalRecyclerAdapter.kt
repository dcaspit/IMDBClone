package com.example.imdbclone.fragments.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.loadAny
import com.example.imdbclone.databinding.MovieCardBinding
import com.example.imdbclone.fragments.main.MainFragmentDirections
import com.example.imdbclone.network.Movie

class HomeHorizontalRecyclerAdapter: RecyclerView.Adapter<HomeHorizontalRecyclerAdapter.MoviePreviewViewHolder>() {

    var movieList = emptyList<Movie>()

    class MoviePreviewViewHolder(
        private val binding: MovieCardBinding
    ):  RecyclerView.ViewHolder(binding.root){

        fun bind(movie: Movie){
            binding.movie = movie
            binding.rowBackground.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToMovieDetails(movie)
                it.findNavController().navigate(action)
            }
            val imgUri = ("https://www.themoviedb.org/t/p/w220_and_h330_face" + movie.poster_path).toUri().buildUpon().scheme("https").build()
            binding.movieImage.load(imgUri)
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): MoviePreviewViewHolder {
                val binding = MovieCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    fun setData(moviesData: List<Movie>){
        val movieDiffUtil = MovieDiffUtil(movieList, moviesData)
        val movieDiffResult = DiffUtil.calculateDiff(movieDiffUtil)
        this.movieList = moviesData
        movieDiffResult.dispatchUpdatesTo(this)
    }
}