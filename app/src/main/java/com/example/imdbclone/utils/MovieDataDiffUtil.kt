package com.example.imdbclone.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.imdbclone.data.models.MovieData

class MovieDataDiffUtil(
    private val oldList: List<MovieData>,
    private val newList: List<MovieData>
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