package com.example.imdbclone.fragments.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.imdbclone.network.Movie

class MovieDiffUtil(
    private val oldList: List<Movie>,
    private val newList: List<Movie>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].title == newList[newItemPosition].title
    }
}