package com.urban.androidhomework.remote.mappers.character

import com.urban.androidhomework.remote.data.models.character.CharacterLocationEntity
import com.urban.androidhomework.remote.mappers.RemoteModelMapper
import com.urban.androidhomework.remote.models.charcater.CharacterLocationNetworkModel
import javax.inject.Inject

class CharacterLocationNetworkModelMapper @Inject constructor() :
    RemoteModelMapper<CharacterLocationNetworkModel, CharacterLocationEntity> {

    override fun mapFromModel(model: CharacterLocationNetworkModel): CharacterLocationEntity {
        return CharacterLocationEntity(
            safeString(model.name),
            safeString(model.url)
        )
    }
}