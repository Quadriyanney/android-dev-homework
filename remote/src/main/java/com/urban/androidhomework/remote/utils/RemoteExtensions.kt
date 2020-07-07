package com.urban.androidhomework.remote.utils

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

fun handleError(exception: Throwable): Throwable {
    return if (exception is HttpException) {
        val responseBody = exception.response()?.errorBody()
        Throwable(responseBody.toString())
    } else if (exception is SocketTimeoutException || exception is ConnectException) {
        Throwable("Please check your internet connection and retry.")
    } else {
        Throwable("There was an error handling your request, please retry.")
    }
}