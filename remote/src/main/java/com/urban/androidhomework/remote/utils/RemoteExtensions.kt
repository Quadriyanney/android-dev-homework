package com.urban.androidhomework.remote.utils

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

fun handleError(exception: Throwable): Throwable {
    return if (exception is HttpException) {
        val responseBody = exception.response()?.errorBody()
        Throwable(getErrorMessage(responseBody))
    } else if (exception is SocketTimeoutException || exception is ConnectException) {
        Throwable("Please check your internet connection and retry.")
    } else {
        Throwable("There was an error handling your request, please retry.")
    }
}

private fun getErrorMessage(responseBody: ResponseBody?): String? {
    return try {
        val jsonObject = JSONObject(responseBody?.string()!!)
        val message = when {
            jsonObject.has("message") -> jsonObject.getString("message")
            jsonObject.has("error") -> jsonObject.getString("error")
            jsonObject.has("err") -> jsonObject.getString("err")
            else -> ""
        }
        message
    } catch (e: Exception) {
        "There was an error handling your request, please retry."
    }
}