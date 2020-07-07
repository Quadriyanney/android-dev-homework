package com.urban.androidhomework.utils

import com.urban.androidhomework.domain.executor.IExecutionThread
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Handle thread on which use-cases are run
 */
class ExecutionThread @Inject constructor() : IExecutionThread {

    override val scheduler: Scheduler
        get() = Schedulers.io()

    override val observerThread: Scheduler
        get() = AndroidSchedulers.mainThread()
}