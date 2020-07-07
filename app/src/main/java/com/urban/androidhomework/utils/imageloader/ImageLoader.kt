package com.urban.androidhomework.utils.imageloader

import android.widget.ImageView
import androidx.annotation.DrawableRes

interface ImageLoader {

    fun loadImage(url: String, imageView: ImageView)

    fun loadImage(url: String, imageView: ImageView, @DrawableRes placeholder: Int)
}
