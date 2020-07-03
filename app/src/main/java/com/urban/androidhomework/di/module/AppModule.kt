package com.urban.androidhomework.di.module

import android.content.Context
import com.urban.androidhomework.UrbanHomeworkApp
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @Provides
    fun providesContext(app: UrbanHomeworkApp): Context {
        return app
    }
//
//    @Provides
//    fun providesImageLoader(imageLoader: GlideImageLoader): ImageLoader {
//        return imageLoader
//    }
//
//    @Singleton
//    @Provides
//    fun providesGlide(context: Context): RequestManager {
//        return Glide.with(context)
//    }
}