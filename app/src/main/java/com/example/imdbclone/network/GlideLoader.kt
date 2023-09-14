package com.example.imdbclone.network

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class GlideLoader(
    val context: Context,
) {

    fun loadImageWithCaching(imageUrl: String, imageView: ImageView) {
        Glide.with(context).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//            .placeholder(R.drawable.placeholder) // Optional: Add a placeholder image
//            .error(R.drawable.error) // Optional: Add an error image
            .into(imageView)
    }


}