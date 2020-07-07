package com.urban.androidhomework.utils

sealed class State<out T : Any> {

    data class Success<out T : Any>(val data: T) : State<T>()

    data class Error(val error: Throwable) : State<Nothing>()

    object Loading : State<Nothing>()
}