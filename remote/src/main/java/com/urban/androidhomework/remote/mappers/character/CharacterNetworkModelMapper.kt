package com.urban.androidhomework.remote.mappers.character

import com.urban.androidhomework.data.models.character.CharacterEntity
import com.urban.androidhomework.remote.mappers.RemoteModelMapper
import com.urban.androidhomework.remote.models.charcater.CharacterNetworkModel
import javax.inject.Inject

class CharacterNetworkModelMapper @Inject constructor(
    private val characterLocationNetworkModelMapper: CharacterLocationNetworkModelMapper
) : RemoteModelMapper<CharacterNetworkModel, CharacterEntity> {

    override fun mapFromModel(model: CharacterNetworkModel): CharacterEntity {
        return CharacterEntity(
            safeInt(model.id),
            safeString(model.name),
            safeString(model.status),
            safeString(model.species),
            safeString(model.type),
            safeString(model.gender),
            safeString(model.image),
            safeString(model.created),
            model.location?.let { characterLocationNetworkModelMapper.mapFromModel(it) }
        )
    }
}
