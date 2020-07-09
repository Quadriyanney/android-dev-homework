package com.urban.androidhomework.presentation.mappers.character

import com.urban.androidhomework.domain.models.character.Character
import com.urban.androidhomework.presentation.mappers.Mapper
import com.urban.androidhomework.presentation.models.character.CharacterModel
import javax.inject.Inject

class CharacterModelMapper @Inject constructor(
    private val characterLocationModelMapper: CharacterLocationModelMapper
) : Mapper<Character, CharacterModel> {

    override fun mapToUI(domain: Character): CharacterModel {
        return CharacterModel(
            domain.id,
            domain.name,
            domain.status,
            domain.species,
            domain.type,
            domain.gender,
            domain.image,
            domain.created,
            domain.location?.let { characterLocationModelMapper.mapToUI(it) }
        )
    }

    override fun mapToDomain(ui: CharacterModel): Character {
        return Character(
            ui.id,
            ui.name,
            ui.status,
            ui.species,
            ui.type,
            ui.gender,
            ui.image,
            ui.created,
            ui.location?.let { characterLocationModelMapper.mapToDomain(it) }
        )
    }
}
