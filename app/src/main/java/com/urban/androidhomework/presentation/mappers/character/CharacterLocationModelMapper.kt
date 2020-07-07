package com.urban.androidhomework.presentation.mappers.character

import com.urban.androidhomework.domain.models.character.CharacterLocation
import com.urban.androidhomework.presentation.mappers.Mapper
import com.urban.androidhomework.presentation.models.character.CharacterLocationModel
import javax.inject.Inject

class CharacterLocationModelMapper @Inject constructor() :
    Mapper<CharacterLocation, CharacterLocationModel> {

    override fun mapToUI(domain: CharacterLocation): CharacterLocationModel {
        return CharacterLocationModel(
            domain.name,
            domain.url
        )
    }

    override fun mapToDomain(ui: CharacterLocationModel): CharacterLocation {
        return CharacterLocation(
            ui.name,
            ui.url
        )
    }
}