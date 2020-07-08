package com.urban.androidhomework.remote.data.models.character

data class CharacterEntity(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val created: String,
    val location: CharacterLocationEntity?
)