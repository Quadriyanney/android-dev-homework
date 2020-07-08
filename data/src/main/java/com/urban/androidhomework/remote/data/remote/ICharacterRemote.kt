package com.urban.androidhomework.remote.data.remote

import com.urban.androidhomework.remote.data.models.character.CharacterEntity
import io.reactivex.rxjava3.core.Single

interface ICharacterRemote {

    fun getCharacters(): Single<List<CharacterEntity>>

    fun getCharacter(id: Int): Single<CharacterEntity>

    fun getCharactersByIds(ids: List<Int>): Single<List<CharacterEntity>>
}