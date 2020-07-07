package com.urban.androidhomework.remote.utils

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

private const val NO_INTERNET_ERROR = "Please check your internet connection and retry."
private const val UNKNOWN_ERROR = "There was an error handling your request, please retry."

fun handleError(exception: Throwable): Throwable {
    return when (exception) {
        is HttpException -> {
            val responseBody = exception.response()?.errorBody()
            Throwable(responseBody.toString())
        }
        is SocketTimeoutException, is ConnectException -> {
            Throwable(NO_INTERNET_ERROR)
        }
        else -> {
            Throwable(UNKNOWN_ERROR)
        }
    }
}