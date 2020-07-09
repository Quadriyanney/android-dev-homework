package com.urban.androidhomework.utils

import io.reactivex.rxjava3.observers.DisposableSingleObserver

fun <T> singleObserver(
    onSuccess: (T) -> Unit = { },
    onError: (Throwable) -> Unit = { }
): DisposableSingleObserver<T> {
    return object : DisposableSingleObserver<T>() {

        override fun onSuccess(data: T) {
            onSuccess.invoke(data)
        }

        override fun onError(error: Throwable) {
            onError.invoke(error)
        }
    }
}
