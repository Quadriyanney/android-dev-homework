package com.urban.androidhomework.di.module

import com.urban.androidhomework.domain.executor.IExecutionThread
import com.urban.androidhomework.utils.ExecutionThread
import dagger.Binds
import dagger.Module

@Module
abstract class ThreadExecutionModule {

    @Binds
    abstract fun bindExecutionThread(thread: ExecutionThread): IExecutionThread
}