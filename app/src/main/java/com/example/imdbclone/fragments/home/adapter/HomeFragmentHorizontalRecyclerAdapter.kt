package com.example.imdbclone.fragments.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbclone.databinding.MovieCardBinding
import com.example.imdbclone.fragments.main.MainFragmentDirections
import com.example.imdbclone.models.MovieData
import com.example.imdbclone.network.Movie
import com.example.imdbclone.network.MovieApiResponse

class HomeFragmentHorizontalRecyclerAdapter: RecyclerView.Adapter<HomeFragmentHorizontalRecyclerAdapter.MoviePreviewViewHolder>() {

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

    fun setData(moviesData: List<Movie>?){
//        val toDoDiffUtil = ToDoDiffUtil(dataList, toDoData)
//        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        if(moviesData == null){
            this.movieList = listOf()
            return
        }
        this.movieList = moviesData
        //toDoDiffResult.dispatchUpdatesTo(this)
    }
}