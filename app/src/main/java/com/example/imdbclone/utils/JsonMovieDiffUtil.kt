package com.example.imdbclone.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.imdbclone.data.models.JsonMovie


class JsonMovieDiffUtil(
    private val oldList: List<JsonMovie>,
    private val newList: List<JsonMovie>
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