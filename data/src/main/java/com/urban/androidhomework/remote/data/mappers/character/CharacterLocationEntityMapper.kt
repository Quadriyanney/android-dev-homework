package com.urban.androidhomework.remote.data.mappers.character

import com.urban.androidhomework.remote.data.mappers.EntityMapper
import com.urban.androidhomework.remote.data.models.character.CharacterLocationEntity
import com.urban.androidhomework.domain.models.character.CharacterLocation
import javax.inject.Inject

class CharacterLocationEntityMapper @Inject constructor() :
    EntityMapper<CharacterLocationEntity, CharacterLocation>() {

    override fun mapFromEntity(entity: CharacterLocationEntity): CharacterLocation {
        return CharacterLocation(
            entity.name,
            entity.url
        )
    }

    override fun mapToEntity(domain: CharacterLocation): CharacterLocationEntity {
        return CharacterLocationEntity(
            domain.name,
            domain.url
        )
    }
}