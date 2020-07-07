package com.urban.androidhomework.presentation.models.character

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterLocationModel(
    val name: String,
    val url: String
) : Parcelable