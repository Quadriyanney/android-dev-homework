package com.urban.androidhomework.utils.imageloader

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.urban.androidhomework.R
import com.urban.androidhomework.utils.generateColorDrawable
import javax.inject.Inject

class GlideImageLoader @Inject constructor(
    private val glide: RequestManager
) : ImageLoader {

    override fun loadImage(url: String, imageView: ImageView) {
        val drawable = imageView.generateColorDrawable(R.color.colorGrey)

        glide.load(url)
            .fitCenter()
            .centerCrop()
            .placeholder(drawable)
            .error(drawable)
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