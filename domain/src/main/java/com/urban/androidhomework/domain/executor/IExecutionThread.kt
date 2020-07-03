package com.urban.androidhomework.domain.executor

import io.reactivex.rxjava3.core.Scheduler

interface IExecutionThread {

    val scheduler: Scheduler

    val observerThread: Scheduler
}
