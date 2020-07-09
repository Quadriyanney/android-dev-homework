package com.urban.androidhomework.remote.models.charcater

data class CharacterNetworkModel(
    val id: Int?,
    val name: String?,
    val status: String?,
    val species: String?,
    val type: String?,
    val gender: String?,
    val image: String?,
    val created: String?,
    val location: CharacterLocationNetworkModel?
)
