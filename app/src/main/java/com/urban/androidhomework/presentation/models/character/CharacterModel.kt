package com.urban.androidhomework.presentation.models.character

import  android.os.Parcelable
import com.urban.androidhomework.data.Constants
import com.urban.androidhomework.utils.formatToDate
import java.util.Date
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val created: String,
    val location: CharacterLocationModel?
) : Parcelable {

    val createdDate: Date
        get() = created.formatToDate(Constants.SERVER_TIME_STAMP_PATTERN)
}
