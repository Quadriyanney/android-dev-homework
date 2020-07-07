package com.urban.androidhomework.di.module

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.urban.androidhomework.UrbanHomeworkApp
import com.urban.androidhomework.di.component.scopes.AppScope
import dagger.Module
import dagger.Provides

/**
 * Provide Dependencies needed across the app
 */
@Module
object AppModule {

    @[Provides AppScope]
    fun provideContext(app: UrbanHomeworkApp): Context {
        return app
    }

    @[Provides AppScope]
    fun provideGlide(context: Context): RequestManager {
        return Glide.with(context)
    }
}