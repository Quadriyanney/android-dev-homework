package com.urban.androidhomework.remote.data

import com.urban.androidhomework.remote.data.models.character.CharacterEntity
import com.urban.androidhomework.remote.models.charcater.CharacterNetworkModel
import com.urban.androidhomework.remote.models.networkresponse.GetCharactersResponse
import konveyor.base.randomBuild

internal object CharacterDataFactory {

    val characterModel get() = randomBuild<CharacterNetworkModel>()

    val characterModels get() = MutableList(10) { characterModel }

    val characterEntity get() = randomBuild<CharacterEntity>()

    val characterEntities get() = MutableList(10) { characterEntity }

    val charactersResponse get() = randomBuild<GetCharactersResponse>()
}