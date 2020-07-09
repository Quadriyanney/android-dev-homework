package com.urban.androidhomework.utils

/**
 * Handle state of actions
 */
sealed class State<out T : Any> {

    data class Success<out T : Any>(val data: T) : State<T>()

    data class Error(val error: Throwable) : State<Nothing>()

    object Loading : State<Nothing>()
}
