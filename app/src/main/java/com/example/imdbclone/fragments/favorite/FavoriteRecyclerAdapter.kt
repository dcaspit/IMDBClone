package com.example.imdbclone.fragments.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbclone.data.models.MovieData
import com.example.imdbclone.databinding.MovieTopRatedCardBinding
import com.example.imdbclone.network.GlideLoader
import com.example.imdbclone.network.POSTER_BASE_URL
import com.example.imdbclone.utils.MovieDataDiffUtil

class FavoriteRecyclerAdapter: RecyclerView.Adapter<FavoriteRecyclerAdapter.ViewHolder>() {

    private var movieList = emptyList<MovieData>()

    class ViewHolder(
        private val binding: MovieTopRatedCardBinding
    ):  RecyclerView.ViewHolder(binding.root) {
        private val glideLoader = GlideLoader(binding.root.context)
        fun bind(movieData: MovieData) {
            binding.movieData = movieData
            binding.cardContainer.setOnClickListener {
                val action = FavoritesFragmentDirections.actionFavoritesFragmentToMovieDetails(movieData)
                it.findNavController().navigate(action)
            }
            glideLoader.loadRoundedImageWithCaching(POSTER_BASE_URL + movieData.imgUrl, binding.movieImage)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder{
                val binding = MovieTopRatedCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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