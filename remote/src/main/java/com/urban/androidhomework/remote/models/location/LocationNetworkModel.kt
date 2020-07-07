package com.urban.androidhomework.remote.models.location

data class LocationNetworkModel(
    val id: Int?,
    val name: String?,
    val type: String?,
    val dimension: String?,
    val residents: List<String>?
)