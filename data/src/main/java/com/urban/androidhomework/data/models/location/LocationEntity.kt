package com.urban.androidhomework.data.models.location

data class LocationEntity(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>
)
