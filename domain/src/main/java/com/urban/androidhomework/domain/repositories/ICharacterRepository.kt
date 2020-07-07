package com.urban.androidhomework.domain.repositories

import com.urban.androidhomework.domain.models.character.Character
import io.reactivex.rxjava3.core.Single

interface ICharacterRepository {

    fun getCharacters(): Single<List<Character>>

    fun getCharacter(id: Int): Single<Character>

    fun getCharactersByIds(ids: List<Int>): Single<List<Character>>
}