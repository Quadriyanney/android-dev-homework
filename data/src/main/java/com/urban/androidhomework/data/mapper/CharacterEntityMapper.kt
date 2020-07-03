package com.urban.androidhomework.data.mapper

import com.urban.androidhomework.data.model.CharacterEntity
import com.urban.androidhomework.domain.model.Character
import javax.inject.Inject

class CharacterEntityMapper @Inject constructor() : EntityMapper<CharacterEntity, Character>() {

    override fun mapFromEntity(entity: CharacterEntity): Character {
        return Character(
            entity.id,
            entity.name,
            entity.status,
            entity.species,
            entity.type,
            entity.gender,
            entity.image
        )
    }

    override fun mapToEntity(domain: Character): CharacterEntity {
        return CharacterEntity(
            domain.id,
            domain.name,
            domain.status,
            domain.species,
            domain.type,
            domain.gender,
            domain.image
        )
    }
}