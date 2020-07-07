package com.urban.androidhomework.remote.mappers

interface RemoteModelMapper<in Remote, out Data> {

    fun mapFromModel(model: Remote): Data

    fun mapModelList(models: List<Remote>?): List<Data> {
        return models?.map {
            mapFromModel(it)
        } ?: mutableListOf()
    }

    fun <T> safeList(models: List<T>?): List<T> {
        return models ?: emptyList()
    }

    fun safeString(string: String?): String {
        if (string.isNullOrEmpty()) {
            return "N/A"
        }
        return string
    }

    fun safeInt(int: Int?): Int {
        return int ?: 0
    }

    fun safeBoolean(boolean: Boolean?): Boolean {
        return boolean ?: false
    }
}
