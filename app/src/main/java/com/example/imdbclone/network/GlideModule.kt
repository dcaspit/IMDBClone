package com.example.imdbclone.network

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule
@GlideModule
class GlideModule: AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        // Set memory cache size
        builder.setMemoryCache(LruResourceCache(10 * 1024 * 1024)) // 10 MB

        // Set disk cache with a 1-day expiration
        builder.setDiskCache(
            DiskLruCacheFactory(
            context.cacheDir.absolutePath,
            "glide_cache",
            1024 * 1024 * 100 // 100 MB
        )
        )
    }
}