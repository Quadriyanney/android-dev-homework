package com.urban.androidhomework.presentation.models.location

data class LocationModel(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>
)