package com.urban.androidhomework.remote.data

import com.urban.androidhomework.domain.models.character.Character
import com.urban.androidhomework.presentation.models.character.CharacterModel
import konveyor.base.randomBuild

internal object CharacterDataFactory {

    val characterModel get() = randomBuild<CharacterModel>()

    val characterModels get() = MutableList(10) { characterModel }

    val characterDomainModel get() = randomBuild<Character>()

    val characterDomainModels get() = MutableList(10) { characterDomainModel }
}