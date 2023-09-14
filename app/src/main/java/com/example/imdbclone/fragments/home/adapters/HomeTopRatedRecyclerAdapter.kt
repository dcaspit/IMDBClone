package com.example.imdbclone.fragments.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.imdbclone.databinding.MovieTopRatedCardBinding
import com.example.imdbclone.fragments.main.MainFragmentDirections
import com.example.imdbclone.network.GlideLoader
import com.example.imdbclone.network.Movie

class HomeTopRatedRecyclerAdapter: RecyclerView.Adapter<HomeTopRatedRecyclerAdapter.HomeTopRatedViewHolder>() {

    var movieList = emptyList<Movie>()

    class HomeTopRatedViewHolder(
        val binding: MovieTopRatedCardBinding
    ): RecyclerView.ViewHolder(binding.root){
        val glideLoader = GlideLoader(binding.root.context)
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.cardContainer.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToMovieDetails(movie)
                it.findNavController().navigate(action)
            }
            glideLoader.loadRoundedImageWithCaching("https://www.themoviedb.org/t/p/w220_and_h330_face" + movie.poster_path, binding.movieImage)
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): HomeTopRatedViewHolder{
                val binding = MovieTopRatedCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return HomeTopRatedViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeTopRatedViewHolder.from(parent)

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: HomeTopRatedViewHolder, position: Int) {
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