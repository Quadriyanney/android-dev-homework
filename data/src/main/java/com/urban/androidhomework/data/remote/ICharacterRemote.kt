package com.urban.androidhomework.data.remote

import com.urban.androidhomework.data.models.character.CharacterEntity
import io.reactivex.rxjava3.core.Single

interface ICharacterRemote {

    fun getCharacters(): Single<List<CharacterEntity>>

    fun getCharacter(id: Int): Single<CharacterEntity>

    fun getCharactersByIds(ids: List<Int>): Single<List<CharacterEntity>>
}