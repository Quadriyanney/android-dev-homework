package com.urban.androidhomework.remote.mapper

interface RemoteModelMapper<in Remote, out Data> {

    fun mapFromModel(model: Remote): Data

    fun mapModelList(models: List<Remote>?): List<Data> {
        return models?.map {
            mapFromModel(it)
        } ?: mutableListOf()
    }

    fun safeList(models: List<Any>?): List<Any> {
        return models ?: emptyList()
    }

    fun safeString(string: String?): String {
        return string ?: ""
    }

    fun safeInt(int: Int?): Int {
        return int ?: 0
    }

    fun safeBoolean(boolean: Boolean?): Boolean {
        return boolean ?: false
    }
}
