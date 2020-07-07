package com.urban.androidhomework.di.module

import androidx.lifecycle.ViewModelProvider
import com.urban.androidhomework.di.component.scopes.AppScope
import com.urban.androidhomework.domain.executor.IExecutionThread
import com.urban.androidhomework.utils.ExecutionThread
import com.urban.androidhomework.utils.ViewModelFactory
import com.urban.androidhomework.utils.imageloader.GlideImageLoader
import com.urban.androidhomework.utils.imageloader.ImageLoader
import dagger.Binds
import dagger.Module

/**
 * Provide extra Dependencies
 */
@Module
abstract class UtilsModule {

    @[Binds AppScope]
    abstract fun bindExecutionThread(thread: ExecutionThread): IExecutionThread

    @[Binds AppScope]
    abstract fun bindImageLoader(imageLoader: GlideImageLoader): ImageLoader

    @[Binds AppScope]
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}