package com.example.imdbclone.fragments.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbclone.databinding.MovieCardBinding
import com.example.imdbclone.fragments.home.HomePageDirections
import com.example.imdbclone.fragments.main.MainFragmentDirections
import com.example.imdbclone.models.MovieData
import com.example.imdbclone.models.listOfMovies

class MoviePreviewAdapter: RecyclerView.Adapter<MoviePreviewAdapter.MoviePreviewViewHolder>() {

    var movieList = emptyList<MovieData>()

    class MoviePreviewViewHolder(
        private val binding: MovieCardBinding
    ):  RecyclerView.ViewHolder(binding.root){

        fun bind(movieData: MovieData){
            binding.movieData = movieData
            binding.rowBackground.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToMovieDetails(movieData)
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

    fun setData(moviesData: List<MovieData>?){
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