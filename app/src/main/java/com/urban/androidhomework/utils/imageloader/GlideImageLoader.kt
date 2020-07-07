package com.urban.androidhomework.utils.imageloader

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class GlideImageLoader @Inject constructor(
    private val glide: RequestManager
) : ImageLoader {

    override fun loadImage(url: String, imageView: ImageView) {
        glide.load(url)
            .fitCenter()
            .centerCrop()
            .into(imageView)
    }

    override fun loadImage(url: String, imageView: ImageView, placeholder: Int) {
        glide.load(url)
            .fitCenter()
            .centerCrop()
            .placeholder(placeholder)
            .error(placeholder)
            .into(imageView)
    }
}