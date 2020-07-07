package com.urban.androidhomework.data.mappers.character

import com.urban.androidhomework.data.mappers.EntityMapper
import com.urban.androidhomework.data.models.character.CharacterEntity
import com.urban.androidhomework.domain.models.character.Character
import javax.inject.Inject

class CharacterEntityMapper @Inject constructor(
    private val characterLocationEntityMapper: CharacterLocationEntityMapper
) : EntityMapper<CharacterEntity, Character>() {

    override fun mapFromEntity(entity: CharacterEntity): Character {
        return Character(
            entity.id,
            entity.name,
            entity.status,
            entity.species,
            entity.type,
            entity.gender,
            entity.image,
            entity.created,
            entity.location?.let { characterLocationEntityMapper.mapFromEntity(it) }
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
            domain.image,
            domain.created,
            domain.location?.let { characterLocationEntityMapper.mapToEntity(it) }
        )
    }
}