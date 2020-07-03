package com.urban.androidhomework.remote.mapper

import com.urban.androidhomework.data.model.CharacterEntity
import com.urban.androidhomework.remote.model.CharacterNetworkModel
import javax.inject.Inject

class CharacterNetworkModelMapper @Inject constructor() :
    RemoteModelMapper<CharacterNetworkModel, CharacterEntity> {

    override fun mapFromModel(model: CharacterNetworkModel): CharacterEntity {
        return CharacterEntity(
            safeInt(model.id),
            safeString(model.name),
            safeString(model.status),
            safeString(model.species),
            safeString(model.type),
            safeString(model.gender),
            safeString(model.image)
        )
    }
}