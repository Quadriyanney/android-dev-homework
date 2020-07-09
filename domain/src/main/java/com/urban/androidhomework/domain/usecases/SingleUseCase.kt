package com.urban.androidhomework.domain.usecases

import com.urban.androidhomework.domain.executor.IExecutionThread
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver

abstract class SingleUseCase<in Params, T>(
    private val executionThread: IExecutionThread
) {

    private val disposables = CompositeDisposable()

    abstract fun build(params: Params? = null): Single<T>

    operator fun invoke(observer: DisposableSingleObserver<T>, params: Params? = null) {
        execute(observer, params)
    }

    private fun execute(observer: DisposableSingleObserver<T>, params: Params?) {
        val single = build(params)
            .subscribeOn(executionThread.scheduler)
            .observeOn(executionThread.observerThread)
        addDisposable(single.subscribeWith(observer))
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

    companion object {
        const val NO_PARAMS_ERROR = "Params can't be null for this use case !!!"
    }
}
