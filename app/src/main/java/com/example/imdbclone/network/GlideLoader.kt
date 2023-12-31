package com.example.imdbclone.network

import android.content.Context
import android.widget.ImageView
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.imdbclone.R

class GlideLoader(val context: Context) {

    fun loadRoundedImageWithCaching(imageUrl: String, imageView: ImageView) {
        Glide.with(context).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.ic_poster_placeholder_background)
            .transform(RoundedCorners(16)).into(imageView)
    }

    fun loadImageWithCaching(imageUrl: String, imageView: ImageView) {
        Glide.with(context).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.ic_poster_placeholder_background)
            .into(imageView)
    }

}